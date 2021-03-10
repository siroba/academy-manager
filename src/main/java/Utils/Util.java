package Utils;

import java.lang.reflect.InvocationTargetException;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Various utilities with general serialization methods, csv conversion and date conversion.
 */
public class Util {
	private Util() {
	    throw new IllegalStateException("Utility class");
	}

	/**
	 * Serialize a list of objects to json format by inserting line breaks between each element. 
	 * to facilitate the comparison of test results using jackson-databind
	 * (optionally allows to get a csv-like representation).
	 * @param pojoList List of objects to serialize.
	 * @param asArray if true encodes the different fields of the object as an array 
	 * and removes quotes for easy comparison, if false returns the complete json.
	 * @return the string representing the serialized list
	 */
	public static String serializeToJson(Class<?> pojoClass, List<?> pojoList, boolean asArray) {
		try {
			ObjectMapper mapper=new ObjectMapper();
			if (asArray) {
		        mapper.configOverride(pojoClass).setFormat(JsonFormat.Value.forShape(JsonFormat.Shape.ARRAY));
		        String value=mapper.writeValueAsString(pojoList);
		    	return value.replace("],", "],\n").replace("\"", ""); //with line breaks and without quotes
				//another alternative is to use the csv specific classes provided by Jackson (jackson-dataformat-csv)
			} else {
				return mapper.writeValueAsString(pojoList).replaceAll("},", "},\n"); //with line breaks
			}
		} catch (JsonProcessingException e) {
			throw new ApplicationException(e);
		}
	}
	
	/**
	 * Convert a list of objects to csv format.
	 * @param pojoList List of objects to serialize
	 * @param fields fields of each object to be included in the csv
	 */
	public static String pojosToCsv(List<?> pojoList, String[] fields) {
		return pojosToCsv(pojoList,fields,false,",","","","");
	}
	/**
	 * Convert a list of objects to csv format with various parameters to customize the look and feel.
	 * @param pojoList List of objects to serialize
	 * @param fields fields of each object to be included in the csv
	 * @param headers if true includes a first row with headers
	 * @param separator character separating each column
	 * @param begin character to include at the beginning of each line
	 * @param end character to be included at the end of each line
	 * @param nullAs Text to be included when the value is null
	 * @return the string that represents the serialized list in csv
	 */
	public static String pojosToCsv(List<?> pojoList, String[] fields, boolean headers, String separator, String begin, String end, String nullAs) {
		StringBuilder sb=new StringBuilder();
		if (headers) 
			addPojoLineToCsv(sb,null,fields,separator,begin,end,nullAs);
		for (int i=0; i<pojoList.size(); i++) {
			try {
				//use Apache commons BeanUtils to get the attributes of the object on a map
				Map<String, String> objectAsMap = BeanUtils.describe(pojoList.get(i));
				addPojoLineToCsv(sb,objectAsMap,fields,separator,begin,end,nullAs);
			} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
				throw new ApplicationException(e);
			}
		}
		return sb.toString();
	}
	private static void addPojoLineToCsv(StringBuilder sb, Map<String, String> objectAsMap, String[] fields, String separator, String begin, String end, String nullAs) {
		sb.append(begin);
		for (int j=0; j<fields.length; j++) {
			String value;
			if (objectAsMap==null) //field name if no map
				value = fields[j];
			else //value of the field or the one specified for null
				value = objectAsMap.get(fields[j])==null ? nullAs : objectAsMap.get(fields[j]);
			sb.append((j==0 ? "" : separator) + value);
		}
		sb.append(end + "\n");
	}

	/**
	 * Convert a two-dimensional array of strings to csv (used for ui comparisons with AssertJ Swing)
	 */
	public static String arraysToCsv(String[][] arrays) {
		return arraysToCsv(arrays,null,",","","");
	}
	/**
	 * Convert a two dimensional array of strings to csv allowing parameterization.
	 * (used for ui comparisons with AssertJ Swing and JBehave)
	 */
	public static String arraysToCsv(String[][] arrays, String[] fields, String separator, String begin, String end) {
		StringBuilder sb=new StringBuilder();
		if (fields!=null)
			addArrayLineToCsv(sb,fields,separator,begin,end);
		for (int i=0; i<arrays.length; i++) 
			addArrayLineToCsv(sb,arrays[i],separator,begin,end);
		return sb.toString();
	}
	private static void addArrayLineToCsv(StringBuilder sb, String[] array, String separator, String begin, String end) {
		sb.append(begin);
		for (int j=0; j<array.length; j++)
			sb.append((j==0 ? "" : separator) + array[j]);
		sb.append(end);
		sb.append("\n");
	}
	
	/** 
	 * Convert date repesented as an iso string to java date (for conversion of date type entries)
	 */
	public static Date isoStringToDate(String isoDateString) {
		try {
		return new SimpleDateFormat("yyyy-MM-dd").parse(isoDateString);
		} catch (ParseException e) {
			throw new ApplicationException("Incorrect ISO format for date: "+isoDateString);
		}
	}
	/** 
	 * Convert java date to an iso format string (for display or sql use) 
	 */
	public static String dateToIsoString(Date javaDate) {
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(javaDate);
	}
	
}