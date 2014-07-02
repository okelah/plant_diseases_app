package plant.diseases.android.util;

import java.util.*;

/***
 * 字符串处理工具类
 */
public final class StringUtil {

	public static final String EMPTY = "";

	public static final int INDEX_NOT_FOUND = -1;

	static final String FOLDER_SEPARATOR = "/";

	private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	public static final String TOP_PATH = "..";

	public static final String CURRENT_PATH = ".";

	public static final String WRAP_LINE = "\n";

	// 字母Z使用了两个标签，这里有２７个值
	// i, u, v都不做声母, 跟随前面的字母
	private static final char[] chartable = {'啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '哈', '击', '喀', '垃', '妈', '拿', '哦',
		'啪', '期', '然', '撒', '塌', '塌', '塌', '挖', '昔', '压', '匝', '座'};

	private static final char[] alphatable = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
		'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

	private static final int[] table = new int[27];
	// 静�?初始�?
	static {
		for (int i = 0; i < 27; ++i) {
			table[i] = gbCode(chartable[i]);
		}
	}

	private StringUtil() {
		super();
	}

	// Empty checks
	// -----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if a String is empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isEmpty(null)      = true
	 * StringUtil.isEmpty("")        = true
	 * StringUtil.isEmpty(" ")       = false
	 * StringUtil.isEmpty("bob")     = false
	 * StringUtil.isEmpty("  bob  ") = false
	 * </pre>
	 * 
	 * <p>
	 * NOTE: This method changed in Lang version 2.0. It no longer trims the String. That functionality is available in
	 * isBlank().
	 * </p>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is empty or null
	 */
	public static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}

	/**
	 * <p>
	 * Checks if a String is not empty ("") and not null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNotEmpty(null)      = false
	 * StringUtil.isNotEmpty("")        = false
	 * StringUtil.isNotEmpty(" ")       = true
	 * StringUtil.isNotEmpty("bob")     = true
	 * StringUtil.isNotEmpty("  bob  ") = true
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null
	 */
	public static boolean isNotEmpty(String str) {
		return !StringUtil.isEmpty(str);
	}

	/**
	 * <p>
	 * Checks if a String is whitespace, empty ("") or null.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isBlank(null)      = true
	 * StringUtil.isBlank("")        = true
	 * StringUtil.isBlank(" ")       = true
	 * StringUtil.isBlank("bob")     = false
	 * StringUtil.isBlank("  bob  ") = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a String is not empty (""), not null and not whitespace only.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isNotBlank(null)      = false
	 * StringUtil.isNotBlank("")        = false
	 * StringUtil.isNotBlank(" ")       = false
	 * StringUtil.isNotBlank("bob")     = true
	 * StringUtil.isNotBlank("  bob  ") = true
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if the String is not empty and not null and not whitespace
	 * @since 2.0
	 */
	public static boolean isNotBlank(String str) {
		return !StringUtil.isBlank(str);
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String, handling <code>null</code> by returning
	 * <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip
	 * whitespace use {@link #strip(String)}.
	 * </p>
	 * 
	 * <p>
	 * To trim your choice of characters, use the {@link #strip(String, String)} methods.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.trim(null)          = null
	 * StringUtil.trim("")            = ""
	 * StringUtil.trim("     ")       = ""
	 * StringUtil.trim("abc")         = "abc"
	 * StringUtil.trim("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed string, <code>null</code> if null String input
	 */
	public static String trim(String str) {
		return str == null ? null : str.trim();
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String returning an empty String ("") if the
	 * String is empty ("") after the trim or if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip
	 * whitespace use {@link #stripToEmpty(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.trimToEmpty(null)          = ""
	 * StringUtil.trimToEmpty("")            = ""
	 * StringUtil.trimToEmpty("     ")       = ""
	 * StringUtil.trimToEmpty("abc")         = "abc"
	 * StringUtil.trimToEmpty("    abc    ") = "abc"
	 * StringUtil.trimToEmpty("null")        =   "";
	 * </pre>
	 * 
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String, or an empty String if <code>null</code> input
	 * @since 2.0
	 */
	public static String trimToEmpty(String str) {
		if (str == null)
			return EMPTY;
		str = str.trim();
		return "null".equals(str) ? EMPTY : str;
	}

	/**
	 * <p>
	 * Removes control characters (char &lt;= 32) from both ends of this String returning <code>null</code> if the
	 * String is empty ("") after the trim or if it is <code>null</code>.
	 * 
	 * <p>
	 * The String is trimmed using {@link String#trim()}. Trim removes start and end characters &lt;= 32. To strip
	 * whitespace use {@link #stripToNull(String)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.trimToNull(null)          = null
	 * StringUtil.trimToNull("")            = null
	 * StringUtil.trimToNull("     ")       = null
	 * StringUtil.trimToNull("abc")         = "abc"
	 * StringUtil.trimToNull("    abc    ") = "abc"
	 * </pre>
	 * 
	 * @param str the String to be trimmed, may be null
	 * @return the trimmed String, <code>null</code> if only chars &lt;= 32, empty or null String input
	 * @since 2.0
	 */
	public static String trimToNull(String str) {
		String ts = trim(str);
		return isEmpty(ts) ? null : ts;
	}

	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
	 * The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.equals(null, null)   = true
	 * StringUtil.equals(null, "abc")  = false
	 * StringUtil.equals("abc", null)  = false
	 * StringUtil.equals("abc", "abc") = true
	 * StringUtil.equals("abc", "ABC") = false
	 * </pre>
	 * 
	 * @see String#equals(Object)
	 * @param str1 the first String, may be null
	 * @param str2 the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case sensitive, or both <code>null</code>
	 */
	public static boolean equals(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/**
	 * <p>
	 * Compares two Strings, returning <code>true</code> if they are equal ignoring the case.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered equal.
	 * Comparison is case insensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.equalsIgnoreCase(null, null)   = true
	 * StringUtil.equalsIgnoreCase(null, "abc")  = false
	 * StringUtil.equalsIgnoreCase("abc", null)  = false
	 * StringUtil.equalsIgnoreCase("abc", "abc") = true
	 * StringUtil.equalsIgnoreCase("abc", "ABC") = true
	 * </pre>
	 * 
	 * @see String#equalsIgnoreCase(String)
	 * @param str1 the first String, may be null
	 * @param str2 the second String, may be null
	 * @return <code>true</code> if the Strings are equal, case insensitive, or both <code>null</code>
	 */
	public static boolean equalsIgnoreCase(String str1, String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	// IndexOf
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This method uses {@link String#indexOf(int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>INDEX_NOT_FOUND (-1)</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)         = -1
	 * StringUtil.indexOf("", *)           = -1
	 * StringUtil.indexOf("aabaabaa", 'a') = 0
	 * StringUtil.indexOf("aabaabaa", 'b') = 2
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchChar the character to find
	 * @return the first index of the search character, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, char searchChar) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchChar);
	}

	/**
	 * <p>
	 * Finds the first index within a String from a start position, handling <code>null</code>. This method uses
	 * {@link String#indexOf(int, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>(INDEX_NOT_FOUND) -1</code>. A negative start position
	 * is treated as zero. A start position greater than the string length returns <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf("", *, *)            = -1
	 * StringUtil.indexOf("aabaabaa", 'b', 0)  = 2
	 * StringUtil.indexOf("aabaabaa", 'b', 3)  = 5
	 * StringUtil.indexOf("aabaabaa", 'b', 9)  = -1
	 * StringUtil.indexOf("aabaabaa", 'b', -1) = 2
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchChar the character to find
	 * @param startPos the start position, negative treated as zero
	 * @return the first index of the search character, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, char searchChar, int startPos) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchChar, startPos);
	}

	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This method uses
	 * {@link String#indexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *)          = -1
	 * StringUtil.indexOf(*, null)          = -1
	 * StringUtil.indexOf("", "")           = 0
	 * StringUtil.indexOf("", *)            = -1 (except when * = "")
	 * StringUtil.indexOf("aabaabaa", "a")  = 0
	 * StringUtil.indexOf("aabaabaa", "b")  = 2
	 * StringUtil.indexOf("aabaabaa", "ab") = 1
	 * StringUtil.indexOf("aabaabaa", "")   = 0
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.indexOf(searchStr);
	}

	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This method uses
	 * {@link String#indexOf(String, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start position is treated as zero. An empty
	 * ("") search String always matches. A start position greater than the string length only matches an empty search
	 * String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOf(null, *, *)          = -1
	 * StringUtil.indexOf(*, null, *)          = -1
	 * StringUtil.indexOf("", "", 0)           = 0
	 * StringUtil.indexOf("", *, 0)            = -1 (except when * = "")
	 * StringUtil.indexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.indexOf("aabaabaa", "b", 0)  = 2
	 * StringUtil.indexOf("aabaabaa", "ab", 0) = 1
	 * StringUtil.indexOf("aabaabaa", "b", 3)  = 5
	 * StringUtil.indexOf("aabaabaa", "b", 9)  = -1
	 * StringUtil.indexOf("aabaabaa", "b", -1) = 2
	 * StringUtil.indexOf("aabaabaa", "", 2)   = 2
	 * StringUtil.indexOf("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @param startPos the start position, negative treated as zero
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int indexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		// JDK1.2/JDK1.3 have a bug, when startPos > str.length for "", hence
		if (searchStr.length() == 0 && startPos >= str.length()) {
			return str.length();
		}
		return str.indexOf(searchStr, startPos);
	}

	/**
	 * <p>
	 * Case in-sensitive find of the first index within a String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start position is treated as zero. An empty
	 * ("") search String always matches. A start position greater than the string length only matches an empty search
	 * String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOfIgnoreCase(null, *)          = -1
	 * StringUtil.indexOfIgnoreCase(*, null)          = -1
	 * StringUtil.indexOfIgnoreCase("", "")           = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "a")  = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "b")  = 2
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "ab") = 1
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.5
	 */
	public static int indexOfIgnoreCase(String str, String searchStr) {
		return indexOfIgnoreCase(str, searchStr, 0);
	}

	/**
	 * <p>
	 * Case in-sensitive find of the first index within a String from the specified position.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start position is treated as zero. An empty
	 * ("") search String always matches. A start position greater than the string length only matches an empty search
	 * String.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOfIgnoreCase(null, *, *)          = -1
	 * StringUtil.indexOfIgnoreCase(*, null, *)          = -1
	 * StringUtil.indexOfIgnoreCase("", "", 0)           = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", 0)  = 2
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "AB", 0) = 1
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", 3)  = 5
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", 9)  = -1
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "B", -1) = 2
	 * StringUtil.indexOfIgnoreCase("aabaabaa", "", 2)   = 2
	 * StringUtil.indexOfIgnoreCase("abc", "", 9)        = 3
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @param startPos the start position, negative treated as zero
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.5
	 */
	public static int indexOfIgnoreCase(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos < 0) {
			startPos = 0;
		}
		int endLimit = (str.length() - searchStr.length()) + 1;
		if (startPos > endLimit) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}
		for (int i = startPos; i < endLimit; i++) {
			if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	// LastIndexOf
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Finds the last index within a String, handling <code>null</code>. This method uses
	 * {@link String#lastIndexOf(int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)         = -1
	 * StringUtil.lastIndexOf("", *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a') = 7
	 * StringUtil.lastIndexOf("aabaabaa", 'b') = 5
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchChar the character to find
	 * @return the last index of the search character, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, char searchChar) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchChar);
	}

	/**
	 * <p>
	 * Finds the last index within a String from a start position, handling <code>null</code>. This method uses
	 * {@link String#lastIndexOf(int, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>-1</code>. A negative start position returns
	 * <code>-1</code>. A start position greater than the string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf("", *,  *)           = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 8)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 4)  = 2
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 0)  = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'b', 9)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", 'b', -1) = -1
	 * StringUtil.lastIndexOf("aabaabaa", 'a', 0)  = 0
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchChar the character to find
	 * @param startPos the start position
	 * @return the last index of the search character, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, char searchChar, int startPos) {
		if (isEmpty(str)) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchChar, startPos);
	}

	/**
	 * <p>
	 * Finds the last index within a String, handling <code>null</code>. This method uses
	 * {@link String#lastIndexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *)          = -1
	 * StringUtil.lastIndexOf(*, null)          = -1
	 * StringUtil.lastIndexOf("", "")           = 0
	 * StringUtil.lastIndexOf("aabaabaa", "a")  = 7
	 * StringUtil.lastIndexOf("aabaabaa", "b")  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "ab") = 4
	 * StringUtil.lastIndexOf("aabaabaa", "")   = 8
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @return the last index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchStr);
	}

	/**
	 * <p>
	 * Finds the first index within a String, handling <code>null</code>. This method uses
	 * {@link String#lastIndexOf(String, int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start position returns <code>-1</code>. An
	 * empty ("") search String always matches unless the start position is negative. A start position greater than the
	 * string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOf(null, *, *)          = -1
	 * StringUtil.lastIndexOf(*, null, *)          = -1
	 * StringUtil.lastIndexOf("aabaabaa", "a", 8)  = 7
	 * StringUtil.lastIndexOf("aabaabaa", "b", 8)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "ab", 8) = 4
	 * StringUtil.lastIndexOf("aabaabaa", "b", 9)  = 5
	 * StringUtil.lastIndexOf("aabaabaa", "b", -1) = -1
	 * StringUtil.lastIndexOf("aabaabaa", "a", 0)  = 0
	 * StringUtil.lastIndexOf("aabaabaa", "b", 0)  = -1
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @param startPos the start position, negative treated as zero
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.0
	 */
	public static int lastIndexOf(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return str.lastIndexOf(searchStr, startPos);
	}

	/**
	 * <p>
	 * Case in-sensitive find of the last index within a String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start position returns <code>-1</code>. An
	 * empty ("") search String always matches unless the start position is negative. A start position greater than the
	 * string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOfIgnoreCase(null, *)          = -1
	 * StringUtil.lastIndexOfIgnoreCase(*, null)          = -1
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A")  = 7
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B")  = 5
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "AB") = 4
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.5
	 */
	public static int lastIndexOfIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		return lastIndexOfIgnoreCase(str, searchStr, str.length());
	}

	/**
	 * <p>
	 * Case in-sensitive find of the last index within a String from the specified position.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>-1</code>. A negative start position returns <code>-1</code>. An
	 * empty ("") search String always matches unless the start position is negative. A start position greater than the
	 * string length searches the whole string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.lastIndexOfIgnoreCase(null, *, *)          = -1
	 * StringUtil.lastIndexOfIgnoreCase(*, null, *)          = -1
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A", 8)  = 7
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 8)  = 5
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "AB", 8) = 4
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 9)  = 5
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", -1) = -1
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "A", 0)  = 0
	 * StringUtil.lastIndexOfIgnoreCase("aabaabaa", "B", 0)  = -1
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @param startPos the start position
	 * @return the first index of the search String, -1 if no match or <code>null</code> string input
	 * @since 2.5
	 */
	public static int lastIndexOfIgnoreCase(String str, String searchStr, int startPos) {
		if (str == null || searchStr == null) {
			return INDEX_NOT_FOUND;
		}
		if (startPos > (str.length() - searchStr.length())) {
			startPos = str.length() - searchStr.length();
		}
		if (startPos < 0) {
			return INDEX_NOT_FOUND;
		}
		if (searchStr.length() == 0) {
			return startPos;
		}

		for (int i = startPos; i >= 0; i--) {
			if (str.regionMatches(true, i, searchStr, 0, searchStr.length())) {
				return i;
			}
		}
		return INDEX_NOT_FOUND;
	}

	/**
	 * 字符串补齐函�?
	 * 
	 * @param srcString 要补齐的原字符串,如果srcString是空白或null,则返回totalLength个长度填充字�?
	 * @param padChar 填充的字�?
	 * @param rightPad 是否是在右边补齐 true=>右补�? false=>左边补齐
	 * @param totalLength 输出字符串的总长�?如果totalLength<srcString.length,在直接返回srcString 用法示例�?pad("123",'0',true,6) =>
	 *        "123000" pad("123",'0',false,6) => "000123" pad("123",'0',true,2) => "123" pad("",'0',false,6) => "000000"
	 */
	public final static String pad(String srcString, char padChar, boolean rightPad, int totalLength) {
		if (srcString == null)
			srcString = EMPTY;
		int srcLength = srcString.length();
		if (srcLength >= totalLength) {
			return srcString;
		}

		int padLength = totalLength - srcLength;
		StringBuffer sb = new StringBuffer(padLength);
		for (int i = 0; i < padLength; ++i) {
			sb.append(padChar);
		}

		if (rightPad) {
			return srcString + sb.toString();
		} else {
			return sb.toString() + srcString;
		}
	}

	/**
	 * 字节数组�?6进制字符串表�?Get hex string from byte array
	 */
	public final static String toHexString(byte[] res) {
		if (res == null)
			return EMPTY;
		StringBuffer sb = new StringBuffer(res.length << 1);
		for (int i = 0; i < res.length; i++) {
			String digit = Integer.toHexString(0xFF & res[i]);
			if (digit.length() == 1) {
				digit = '0' + digit;
			}
			sb.append(digit);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 16进制字符串的字节数组 Get byte array from hex string
	 */
	public final static byte[] toByteArray(String hexString) {
		if (hexString == null)
			return new byte[0];
		int arrLength = hexString.length() >> 1;
		byte buff[] = new byte[arrLength];
		for (int i = 0; i < arrLength; i++) {
			int index = i << 1;
			String digit = hexString.substring(index, index + 2);
			buff[i] = (byte) Integer.parseInt(digit, 16);
		}
		return buff;
	}

	// Contains
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Checks if String contains a search character, handling <code>null</code>. This method uses
	 * {@link String#indexOf(int)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)    = false
	 * StringUtil.contains("", *)      = false
	 * StringUtil.contains("abc", 'a') = true
	 * StringUtil.contains("abc", 'z') = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchChar the character to find
	 * @return true if the String contains the search character, false if not or <code>null</code> string input
	 * @since 2.0
	 */
	public static boolean contains(String str, char searchChar) {
		if (isEmpty(str)) {
			return false;
		}
		return str.indexOf(searchChar) >= 0;
	}

	/**
	 * <p>
	 * Checks if String contains a search String, handling <code>null</code>. This method uses
	 * {@link String#indexOf(String)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.contains(null, *)     = false
	 * StringUtil.contains(*, null)     = false
	 * StringUtil.contains("", "")      = true
	 * StringUtil.contains("abc", "")   = true
	 * StringUtil.contains("abc", "a")  = true
	 * StringUtil.contains("abc", "z")  = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @return true if the String contains the search String, false if not or <code>null</code> string input
	 * @since 2.0
	 */
	public static boolean contains(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		return str.indexOf(searchStr) >= 0;
	}

	/**
	 * <p>
	 * Checks if String contains a search String irrespective of case, handling <code>null</code>. Case-insensitivity is
	 * defined as by {@link String#equalsIgnoreCase(String)}.
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.contains(null, *) = false
	 * StringUtil.contains(*, null) = false
	 * StringUtil.contains("", "") = true
	 * StringUtil.contains("abc", "") = true
	 * StringUtil.contains("abc", "a") = true
	 * StringUtil.contains("abc", "z") = false
	 * StringUtil.contains("abc", "A") = true
	 * StringUtil.contains("abc", "Z") = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param searchStr the String to find, may be null
	 * @return true if the String contains the search String irrespective of case or false if not or <code>null</code>
	 *         string input
	 */
	public static boolean containsIgnoreCase(String str, String searchStr) {
		if (str == null || searchStr == null) {
			return false;
		}
		int len = searchStr.length();
		int max = str.length() - len;
		for (int i = 0; i <= max; i++) {
			if (str.regionMatches(true, i, searchStr, 0, len)) {
				return true;
			}
		}
		return false;
	}

	// Substring
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start <code>n</code> characters from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String will return <code>null</code>. An empty ("") String will return "".
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substring(null, *)   = null
	 * StringUtil.substring("", *)     = ""
	 * StringUtil.substring("abc", 0)  = "abc"
	 * StringUtil.substring("abc", 2)  = "c"
	 * StringUtil.substring("abc", 4)  = ""
	 * StringUtil.substring("abc", -2) = "bc"
	 * StringUtil.substring("abc", -4) = "abc"
	 * </pre>
	 * 
	 * @param str the String to get the substring from, may be null
	 * @param start the position to start from, negative means count back from the end of the String by this many
	 *        characters
	 * @return substring from start position, <code>null</code> if null String input
	 */
	public static String substring(String str, int start) {
		if (str == null) {
			return null;
		}

		// handle negatives, which means last n characters
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		if (start < 0) {
			start = 0;
		}
		if (start > str.length()) {
			return EMPTY;
		}

		return str.substring(start);
	}

	/**
	 * <p>
	 * Gets a substring from the specified String avoiding exceptions.
	 * </p>
	 * 
	 * <p>
	 * A negative start position can be used to start/end <code>n</code> characters from the end of the String.
	 * </p>
	 * 
	 * <p>
	 * The returned substring starts with the character in the <code>start</code> position and ends before the
	 * <code>end</code> position. All position counting is zero-based -- i.e., to start at the beginning of the string
	 * use <code>start = 0</code>. Negative start and end positions can be used to specify offsets relative to the end
	 * of the String.
	 * </p>
	 * 
	 * <p>
	 * If <code>start</code> is not strictly to the left of <code>end</code>, "" is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substring(null, *, *)    = null
	 * StringUtil.substring("", * ,  *)    = "";
	 * StringUtil.substring("abc", 0, 2)   = "ab"
	 * StringUtil.substring("abc", 2, 0)   = ""
	 * StringUtil.substring("abc", 2, 4)   = "c"
	 * StringUtil.substring("abc", 4, 6)   = ""
	 * StringUtil.substring("abc", 2, 2)   = ""
	 * StringUtil.substring("abc", -2, -1) = "b"
	 * StringUtil.substring("abc", -4, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str the String to get the substring from, may be null
	 * @param start the position to start from, negative means count back from the end of the String by this many
	 *        characters
	 * @param end the position to end at (exclusive), negative means count back from the end of the String by this many
	 *        characters
	 * @return substring from start position to end positon, <code>null</code> if null String input
	 */
	public static String substring(String str, int start, int end) {
		if (str == null) {
			return null;
		}

		// handle negatives
		if (end < 0) {
			end = str.length() + end; // remember end is negative
		}
		if (start < 0) {
			start = str.length() + start; // remember start is negative
		}

		// check length next
		if (end > str.length()) {
			end = str.length();
		}

		// if start is greater than end, return ""
		if (start > end) {
			return EMPTY;
		}

		if (start < 0) {
			start = 0;
		}
		if (end < 0) {
			end = 0;
		}

		return str.substring(start, end);
	}

	// Left/Right/Mid
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets the leftmost <code>len</code> characters of a String.
	 * </p>
	 * 
	 * <p>
	 * If <code>len</code> characters are not available, or the String is <code>null</code>, the String will be returned
	 * without an exception. An empty String is returned if len is negative.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.left(null, *)    = null
	 * StringUtil.left(*, -ve)     = ""
	 * StringUtil.left("", *)      = ""
	 * StringUtil.left("abc", 0)   = ""
	 * StringUtil.left("abc", 2)   = "ab"
	 * StringUtil.left("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str the String to get the leftmost characters from, may be null
	 * @param len the length of the required String
	 * @return the leftmost characters, <code>null</code> if null String input
	 */
	public static String left(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(0, len);
	}

	/**
	 * <p>
	 * Gets the rightmost <code>len</code> characters of a String.
	 * </p>
	 * 
	 * <p>
	 * If <code>len</code> characters are not available, or the String is <code>null</code>, the String will be returned
	 * without an an exception. An empty String is returned if len is negative.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.right(null, *)    = null
	 * StringUtil.right(*, -ve)     = ""
	 * StringUtil.right("", *)      = ""
	 * StringUtil.right("abc", 0)   = ""
	 * StringUtil.right("abc", 2)   = "bc"
	 * StringUtil.right("abc", 4)   = "abc"
	 * </pre>
	 * 
	 * @param str the String to get the rightmost characters from, may be null
	 * @param len the length of the required String
	 * @return the rightmost characters, <code>null</code> if null String input
	 */
	public static String right(String str, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0) {
			return EMPTY;
		}
		if (str.length() <= len) {
			return str;
		}
		return str.substring(str.length() - len);
	}

	/**
	 * <p>
	 * Gets <code>len</code> characters from the middle of a String.
	 * </p>
	 * 
	 * <p>
	 * If <code>len</code> characters are not available, the remainder of the String will be returned without an
	 * exception. If the String is <code>null</code>, <code>null</code> will be returned. An empty String is returned if
	 * len is negative or exceeds the length of <code>str</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.mid(null, *, *)    = null
	 * StringUtil.mid(*, *, -ve)     = ""
	 * StringUtil.mid("", 0, *)      = ""
	 * StringUtil.mid("abc", 0, 2)   = "ab"
	 * StringUtil.mid("abc", 0, 4)   = "abc"
	 * StringUtil.mid("abc", 2, 4)   = "c"
	 * StringUtil.mid("abc", 4, 2)   = ""
	 * StringUtil.mid("abc", -2, 2)  = "ab"
	 * </pre>
	 * 
	 * @param str the String to get the characters from, may be null
	 * @param pos the position to start from, negative treated as zero
	 * @param len the length of the required String
	 * @return the middle characters, <code>null</code> if null String input
	 */
	public static String mid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}
		if (len < 0 || pos > str.length()) {
			return EMPTY;
		}
		if (pos < 0) {
			pos = 0;
		}
		if (str.length() <= (pos + len)) {
			return str.substring(pos);
		}
		return str.substring(pos, pos + len);
	}

	// SubStringAfter/SubStringBefore
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Gets the substring before the first occurrence of a separator. The separator is not returned.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty
	 * string. A <code>null</code> separator will return the input string.
	 * </p>
	 * 
	 * <p>
	 * If nothing is found, the string input is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substringBefore(null, *)      = null
	 * StringUtil.substringBefore("", *)        = ""
	 * StringUtil.substringBefore("abc", "a")   = ""
	 * StringUtil.substringBefore("abcba", "b") = "a"
	 * StringUtil.substringBefore("abc", "c")   = "ab"
	 * StringUtil.substringBefore("abc", "d")   = "abc"
	 * StringUtil.substringBefore("abc", "")    = ""
	 * StringUtil.substringBefore("abc", null)  = "abc"
	 * </pre>
	 * 
	 * @param str the String to get a substring from, may be null
	 * @param separator the String to search for, may be null
	 * @return the substring before the first occurrence of the separator, <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringBefore(String str, String separator) {
		if (isEmpty(str) || separator == null) {
			return str;
		}
		if (separator.length() == 0) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return str;
		}
		return str.substring(0, pos);
	}

	/**
	 * <p>
	 * Gets the substring after the first occurrence of a separator. The separator is not returned.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input will return <code>null</code>. An empty ("") string input will return the empty
	 * string. A <code>null</code> separator will return the empty string if the input string is not <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * If nothing is found, the empty string is returned.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.substringAfter(null, *)      = null
	 * StringUtil.substringAfter("", *)        = ""
	 * StringUtil.substringAfter(*, null)      = ""
	 * StringUtil.substringAfter("abc", "a")   = "bc"
	 * StringUtil.substringAfter("abcba", "b") = "cba"
	 * StringUtil.substringAfter("abc", "c")   = ""
	 * StringUtil.substringAfter("abc", "d")   = ""
	 * StringUtil.substringAfter("abc", "")    = "abc"
	 * </pre>
	 * 
	 * @param str the String to get a substring from, may be null
	 * @param separator the String to search for, may be null
	 * @return the substring after the first occurrence of the separator, <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String substringAfter(String str, String separator) {
		if (isEmpty(str)) {
			return str;
		}
		if (separator == null) {
			return EMPTY;
		}
		int pos = str.indexOf(separator);
		if (pos == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str.substring(pos + separator.length());
	}

	/**
	 * Normalize the path by suppressing sequences like "path/.." and inner simple dots.
	 * <p>
	 * The result is convenient for path comparison. For other uses, notice that Windows separators ("\") are replaced
	 * by simple slashes.
	 * 
	 * @param path the original path
	 * @return the normalized path
	 */

	public static String cleanPath(String path) {
		if (path == null) {
			return null;
		}
		String pathToUse = replace(path, WINDOWS_FOLDER_SEPARATOR, FOLDER_SEPARATOR);

		// Strip prefix from path to analyze, to not treat it as part of the
		// first path element. This is necessary to correctly parse paths like
		// "file:core/../core/io/Resource.class", where the ".." should just
		// strip the first "core" directory while keeping the "file:" prefix.
		int prefixIndex = pathToUse.indexOf(":");
		String prefix = "";
		if (prefixIndex != -1) {
			prefix = pathToUse.substring(0, prefixIndex + 1);
			pathToUse = pathToUse.substring(prefixIndex + 1);
		}
		if (pathToUse.startsWith(FOLDER_SEPARATOR)) {
			prefix = prefix + FOLDER_SEPARATOR;
			pathToUse = pathToUse.substring(1);
		}

		String[] pathArray = split(pathToUse, FOLDER_SEPARATOR);
		List<String> pathElements = new LinkedList<String>();
		int tops = 0;

		for (int i = pathArray.length - 1; i >= 0; i--) {
			String element = pathArray[i];
			if (CURRENT_PATH.equals(element)) {
				// Points to current directory - drop it.
			} else if (TOP_PATH.equals(element)) {
				// Registering top path found.
				tops++;
			} else {
				if (tops > 0) {
					// Merging path element with element corresponding to top path.
					tops--;
				} else {
					// Normal path element found.
					pathElements.add(0, element);
				}
			}
		}

		// Remaining top paths need to be retained.
		for (int i = 0; i < tops; i++) {
			pathElements.add(0, TOP_PATH);
		}

		return prefix + join(pathElements, FOLDER_SEPARATOR);
	}

	/**
	 * 整理上下文路�?
	 * <ul>
	 * <li>"/" -> ""</li>
	 * <li>"/a/" -> "/a"</li>
	 * <li>"/a//b" -> "/a/b"</li>
	 * </ul>
	 * 
	 * @param contextPath
	 */
	public static String cleanContextPath(String contextPath) {
		String path = EMPTY;
		if (contextPath != null) {
			path = StringUtil.cleanPath(contextPath).replace("//", FOLDER_SEPARATOR);

			// 确保以斜线开�?
			if (!StringUtil.isBlank(path)) {
				if (!path.startsWith(FOLDER_SEPARATOR)) {
					path = FOLDER_SEPARATOR + path;
				}
			}

			if (path.endsWith(FOLDER_SEPARATOR)) {
				return path.substring(0, path.length() - 1);
			}
		}
		return path;
	}

	/**
	 * 分割返回结果
	 * 
	 * @param response 返回字符�?
	 * @return
	 */
	public static Map<String, String> splitResponse(String response) {
		//保存返回结果
		Map<String, String> map = new HashMap<String, String>();
		//判断是否为空
		if (!StringUtil.isEmpty(response)) {
			//已�?&”进行分�?
			String[] array = response.split("&");
			if (array.length > 2) {
				String tokenStr = array[0]; //oauth_token=xxxxx
				String secretStr = array[1];//oauth_token_secret=xxxxxxx
				String[] token = tokenStr.split("=");
				if (token.length == 2) {
					map.put("oauth_token", token[1]);
				}
				String[] secret = secretStr.split("=");
				if (secret.length == 2) {
					map.put("oauth_token_secret", secret[1]);
				}
			}
		}
		return map;
	}

	/**
	 * Copy the given Collection into a String array. The Collection must contain String elements only.
	 * 
	 * @param collection the Collection to copy
	 * @return the String array (<code>null</code> if the passed-in Collection was <code>null</code>)
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * Copy the given Enumeration into a String array. The Enumeration must contain String elements only.
	 * 
	 * @param enumeration the Enumeration to copy
	 * @return the String array (<code>null</code> if the passed-in Enumeration was <code>null</code>)
	 */
	public static String[] toStringArray(Enumeration<String> enumeration) {
		if (enumeration == null) {
			return null;
		}
		List<String> list = Collections.list(enumeration);
		return list.toArray(new String[list.size()]);
	}

	/**
	 * Trim the elements of the given String array, calling <code>String.trim()</code> on each of them.
	 * 
	 * @param array the original String array
	 * @return the resulting array (of the same size) with trimmed elements
	 */
	public static String[] trimArrayElements(String[] array) {
		if (ArrayUtil.isEmpty(array)) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (element != null ? element.trim() : null);
		}
		return result;
	}

	/**
	 * Remove duplicate Strings from the given array. Also sorts the array, as it uses a TreeSet.
	 * 
	 * @param array the String array
	 * @return an array without duplicates, in natural sort order
	 */
	public static String[] removeDuplicateStrings(String[] array) {
		if (ArrayUtil.isEmpty(array)) {
			return array;
		}
		Set<String> set = new TreeSet<String>();
		for (String element : array) {
			set.add(element);
		}
		return toStringArray(set);
	}

	// Splitting
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Splits the provided text into an array, using whitespace as the separator. Whitespace is defined by
	 * {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <p>
	 * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
	 * more control over the split use the StrTokenizer class.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.split(null)       = null
	 * StringUtil.split("")         = []
	 * StringUtil.split("abc def")  = ["abc", "def"]
	 * StringUtil.split("abc  def") = ["abc", "def"]
	 * StringUtil.split(" abc ")    = ["abc"]
	 * </pre>
	 * 
	 * @param str the String to parse, may be null
	 * @return an array of parsed Strings, <code>null</code> if null String input
	 */
	public static String[] split(String str) {
		return split(str, null, -1);
	}

	/**
	 * <p>
	 * Splits the provided text into an array, separator specified. This is an alternative to using StringTokenizer.
	 * </p>
	 * 
	 * <p>
	 * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
	 * more control over the split use the StrTokenizer class.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.split(null, *)         = null
	 * StringUtil.split("", *)           = []
	 * StringUtil.split("a.b.c", '.')    = ["a", "b", "c"]
	 * StringUtil.split("a..b.c", '.')   = ["a", "b", "c"]
	 * StringUtil.split("a:b:c", '.')    = ["a:b:c"]
	 * StringUtil.split("a b c", ' ')    = ["a", "b", "c"]
	 * </pre>
	 * 
	 * @param str the String to parse, may be null
	 * @param separatorChar the character used as the delimiter
	 * @return an array of parsed Strings, <code>null</code> if null String input
	 * @since 2.0
	 */
	public static String[] split(String str, char separatorChar) {
		return splitWorker(str, separatorChar, false);
	}

	/**
	 * <p>
	 * Splits the provided text into an array with a maximum length, separators specified.
	 * </p>
	 * 
	 * <p>
	 * The separator is not included in the returned String array. Adjacent separators are treated as one separator.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
	 * whitespace.
	 * </p>
	 * 
	 * <p>
	 * If more than <code>max</code> delimited substrings are found, the last returned string includes all characters
	 * after the first <code>max - 1</code> returned strings (including separator characters).
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.split(null, *, *)            = null
	 * StringUtil.split("", *, *)              = []
	 * StringUtil.split("ab de fg", null, 0)   = ["ab", "cd", "ef"]
	 * StringUtil.split("ab   de fg", null, 0) = ["ab", "cd", "ef"]
	 * StringUtil.split("ab:cd:ef", ":", 0)    = ["ab", "cd", "ef"]
	 * StringUtil.split("ab:cd:ef", ":", 2)    = ["ab", "cd:ef"]
	 * </pre>
	 * 
	 * @param str the String to parse, may be null
	 * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
	 * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit
	 * @return an array of parsed Strings, <code>null</code> if null String input
	 */
	public static String[] split(String str, String separatorChars, int max) {
		return splitWorker(str, separatorChars, max, false);
	}

	/**
	 * <p>
	 * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
	 * </p>
	 * 
	 * <p>
	 * The separator is not included in the returned String array. Adjacent separators are treated as one separator. For
	 * more control over the split use the StrTokenizer class.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> input String returns <code>null</code>. A <code>null</code> separatorChars splits on
	 * whitespace.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.split(null, *)         = null
	 * StringUtil.split("", *)           = []
	 * StringUtil.split("abc def", null) = ["abc", "def"]
	 * StringUtil.split("abc def", " ")  = ["abc", "def"]
	 * StringUtil.split("abc  def", " ") = ["abc", "def"]
	 * StringUtil.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
	 * </pre>
	 * 
	 * @param str the String to parse, may be null
	 * @param separatorChars the characters used as the delimiters, <code>null</code> splits on whitespace
	 * @return an array of parsed Strings, <code>null</code> if null String input
	 */
	public static String[] split(String str, String separatorChars) {
		return splitWorker(str, separatorChars, -1, false);
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No separator is added to the joined String. Null objects or empty strings within the array are represented by
	 * empty strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.join(null)            = null
	 * StringUtil.join([])              = ""
	 * StringUtil.join([null])          = ""
	 * StringUtil.join(["a", "b", "c"]) = "abc"
	 * StringUtil.join([null, "", "a"]) = "a"
	 * </pre>
	 * 
	 * @param array the array of values to join together, may be null
	 * @return the joined String, <code>null</code> if null array input
	 * @since 2.0
	 */
	public static String join(Object[] array) {
		return join(array, null);
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. Null objects or empty strings within the array are represented by
	 * empty strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.join(null, *)               = null
	 * StringUtil.join([], *)                 = ""
	 * StringUtil.join([null], *)             = ""
	 * StringUtil.join(["a", "b", "c"], ';')  = "a;b;c"
	 * StringUtil.join(["a", "b", "c"], null) = "abc"
	 * StringUtil.join([null, "", "a"], ';')  = ";;a"
	 * </pre>
	 * 
	 * @param array the array of values to join together, may be null
	 * @param separator the separator character to use
	 * @return the joined String, <code>null</code> if null array input
	 * @since 2.0
	 */
	public static String join(Object[] array, char separator) {
		if (array == null) {
			return null;
		}

		return join(array, separator, 0, array.length);
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. Null objects or empty strings within the array are represented by
	 * empty strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.join(null, *)               = null
	 * StringUtil.join([], *)                 = ""
	 * StringUtil.join([null], *)             = ""
	 * StringUtil.join(["a", "b", "c"], ';')  = "a;b;c"
	 * StringUtil.join(["a", "b", "c"], null) = "abc"
	 * StringUtil.join([null, "", "a"], ';')  = ";;a"
	 * </pre>
	 * 
	 * @param array the array of values to join together, may be null
	 * @param separator the separator character to use
	 * @param startIndex the first index to start joining from. It is an error to pass in an end index past the end of
	 *        the array
	 * @param endIndex the index to stop joining from (exclusive). It is an error to pass in an end index past the end
	 *        of the array
	 * @return the joined String, <code>null</code> if null array input
	 * @since 2.0
	 */
	public static String join(Object[] array, char separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return EMPTY;
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + 1);
		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty String
	 * (""). Null objects or empty strings within the array are represented by empty strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.join(null, *)                = null
	 * StringUtil.join([], *)                  = ""
	 * StringUtil.join([null], *)              = ""
	 * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StringUtil.join(["a", "b", "c"], null)  = "abc"
	 * StringUtil.join(["a", "b", "c"], "")    = "abc"
	 * StringUtil.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param array the array of values to join together, may be null
	 * @param separator the separator character to use, null treated as ""
	 * @param startIndex the first index to start joining from. It is an error to pass in an end index past the end of
	 *        the array
	 * @param endIndex the index to stop joining from (exclusive). It is an error to pass in an end index past the end
	 *        of the array
	 * @return the joined String, <code>null</code> if null array input
	 */
	public static String join(Object[] array, String separator, int startIndex, int endIndex) {
		if (array == null) {
			return null;
		}
		if (separator == null) {
			separator = EMPTY;
		}

		// endIndex - startIndex > 0:   Len = NofStrings *(len(firstString) + len(separator))
		//           (Assuming that all Strings are roughly equally long)
		int bufSize = (endIndex - startIndex);
		if (bufSize <= 0) {
			return EMPTY;
		}

		bufSize *= ((array[startIndex] == null ? 16 : array[startIndex].toString().length()) + separator.length());

		StringBuilder buf = new StringBuilder(bufSize);

		for (int i = startIndex; i < endIndex; i++) {
			if (i > startIndex) {
				buf.append(separator);
			}
			if (array[i] != null) {
				buf.append(array[i]);
			}
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * Joins the elements of the provided array into a single String containing the provided list of elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty String
	 * (""). Null objects or empty strings within the array are represented by empty strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.join(null, *)                = null
	 * StringUtil.join([], *)                  = ""
	 * StringUtil.join([null], *)              = ""
	 * StringUtil.join(["a", "b", "c"], "--")  = "a--b--c"
	 * StringUtil.join(["a", "b", "c"], null)  = "abc"
	 * StringUtil.join(["a", "b", "c"], "")    = "abc"
	 * StringUtil.join([null, "", "a"], ',')   = ",,a"
	 * </pre>
	 * 
	 * @param array the array of values to join together, may be null
	 * @param separator the separator character to use, null treated as ""
	 * @return the joined String, <code>null</code> if null array input
	 */
	public static String join(Object[] array, String separator) {
		if (array == null) {
			return null;
		}
		return join(array, separator, 0, array.length);
	}

	/**
	 * <p>
	 * Joins the elements of the provided <code>Collection</code> into a single String containing the provided elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty String
	 * ("").
	 * </p>
	 * 
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 * 
	 * @param collection the <code>Collection</code> of values to join together, may be null
	 * @param separator the separator character to use, null treated as ""
	 * @return the joined String, <code>null</code> if null iterator input
	 * @since 2.3
	 */
	public static String join(Collection<?> collection, String separator) {
		if (collection == null) {
			return null;
		}
		return join(collection.iterator(), separator);
	}

	/**
	 * <p>
	 * Joins the elements of the provided <code>Iterator</code> into a single String containing the provided elements.
	 * </p>
	 * 
	 * <p>
	 * No delimiter is added before or after the list. A <code>null</code> separator is the same as an empty String
	 * ("").
	 * </p>
	 * 
	 * <p>
	 * See the examples here: {@link #join(Object[],String)}.
	 * </p>
	 * 
	 * @param iterator the <code>Iterator</code> of values to join together, may be null
	 * @param separator the separator character to use, null treated as ""
	 * @return the joined String, <code>null</code> if null iterator input
	 */
	public static String join(Iterator<?> iterator, String separator) {

		// handle null, zero and one elements before building a buffer
		if (iterator == null) {
			return null;
		}
		if (!iterator.hasNext()) {
			return EMPTY;
		}
		Object first = iterator.next();
		if (!iterator.hasNext()) {
			return first == null ? EMPTY : first.toString();
		}

		// two or more elements
		StringBuilder buf = new StringBuilder(256); // Java default is 16, probably too small
		if (first != null) {
			buf.append(first);
		}

		while (iterator.hasNext()) {
			if (separator != null) {
				buf.append(separator);
			}
			Object obj = iterator.next();
			if (obj != null) {
				buf.append(obj);
			}
		}
		return buf.toString();
	}

	/**
	 * <p>
	 * Deletes all whitespaces from a String as defined by {@link Character#isWhitespace(char)}.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.deleteWhitespace(null)         = null
	 * StringUtil.deleteWhitespace("")           = ""
	 * StringUtil.deleteWhitespace("abc")        = "abc"
	 * StringUtil.deleteWhitespace("   ab  c  ") = "abc"
	 * </pre>
	 * 
	 * @param str the String to delete whitespace from, may be null
	 * @return the String without whitespaces, <code>null</code> if null String input
	 */
	public static String deleteWhitespace(String str) {
		if (isEmpty(str)) {
			return str;
		}
		int sz = str.length();
		char[] chs = new char[sz];
		int count = 0;
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				chs[count++] = str.charAt(i);
			}
		}
		if (count == sz) {
			return str;
		}
		return new String(chs, 0, count);
	}

	// Replacing
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Replaces a String with another String inside a larger String, once.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> reference passed to this method is a no-op.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.replaceOnce(null, *, *)        = null
	 * StringUtil.replaceOnce("", *, *)          = ""
	 * StringUtil.replaceOnce("any", null, *)    = "any"
	 * StringUtil.replaceOnce("any", *, null)    = "any"
	 * StringUtil.replaceOnce("any", "", *)      = "any"
	 * StringUtil.replaceOnce("aba", "a", null)  = "aba"
	 * StringUtil.replaceOnce("aba", "a", "")    = "ba"
	 * StringUtil.replaceOnce("aba", "a", "z")   = "zba"
	 * </pre>
	 * 
	 * @see #replace(String text, String searchString, String replacement, int max)
	 * @param text text to search and replace in, may be null
	 * @param searchString the String to search for, may be null
	 * @param replacement the String to replace with, may be null
	 * @return the text with any replacements processed, <code>null</code> if null String input
	 */
	public static String replaceOnce(String text, String searchString, String replacement) {
		return replace(text, searchString, replacement, 1);
	}

	/**
	 * <p>
	 * Replaces all occurrences of a String within another String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> reference passed to this method is a no-op.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *)        = null
	 * StringUtil.replace("", *, *)          = ""
	 * StringUtil.replace("any", null, *)    = "any"
	 * StringUtil.replace("any", *, null)    = "any"
	 * StringUtil.replace("any", "", *)      = "any"
	 * StringUtil.replace("aba", "a", null)  = "aba"
	 * StringUtil.replace("aba", "a", "")    = "b"
	 * StringUtil.replace("aba", "a", "z")   = "zbz"
	 * </pre>
	 * 
	 * @see #replace(String text, String searchString, String replacement, int max)
	 * @param text text to search and replace in, may be null
	 * @param searchString the String to search for, may be null
	 * @param replacement the String to replace it with, may be null
	 * @return the text with any replacements processed, <code>null</code> if null String input
	 */
	public static String replace(String text, String searchString, String replacement) {
		return replace(text, searchString, replacement, -1);
	}

	/**
	 * <p>
	 * Replaces a String with another String inside a larger String, for the first <code>max</code> values of the search
	 * String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> reference passed to this method is a no-op.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.replace(null, *, *, *)         = null
	 * StringUtil.replace("", *, *, *)           = ""
	 * StringUtil.replace("any", null, *, *)     = "any"
	 * StringUtil.replace("any", *, null, *)     = "any"
	 * StringUtil.replace("any", "", *, *)       = "any"
	 * StringUtil.replace("any", *, *, 0)        = "any"
	 * StringUtil.replace("abaa", "a", null, -1) = "abaa"
	 * StringUtil.replace("abaa", "a", "", -1)   = "b"
	 * StringUtil.replace("abaa", "a", "z", 0)   = "abaa"
	 * StringUtil.replace("abaa", "a", "z", 1)   = "zbaa"
	 * StringUtil.replace("abaa", "a", "z", 2)   = "zbza"
	 * StringUtil.replace("abaa", "a", "z", -1)  = "zbzz"
	 * </pre>
	 * 
	 * @param text text to search and replace in, may be null
	 * @param searchString the String to search for, may be null
	 * @param replacement the String to replace it with, may be null
	 * @param max maximum number of values to replace, or <code>-1</code> if no maximum
	 * @return the text with any replacements processed, <code>null</code> if null String input
	 */
	public static String replace(String text, String searchString, String replacement, int max) {
		if (isEmpty(text) || isEmpty(searchString) || replacement == null || max == 0) {
			return text;
		}
		int start = 0;
		int end = text.indexOf(searchString, start);
		if (end == INDEX_NOT_FOUND) {
			return text;
		}
		int replLength = searchString.length();
		int increase = replacement.length() - replLength;
		increase = (increase < 0 ? 0 : increase);
		increase *= (max < 0 ? 16 : (max > 64 ? 64 : max));
		StringBuilder buf = new StringBuilder(text.length() + increase);
		while (end != INDEX_NOT_FOUND) {
			buf.append(text.substring(start, end)).append(replacement);
			start = end + replLength;
			if (--max == 0) {
				break;
			}
			end = text.indexOf(searchString, start);
		}
		buf.append(text.substring(start));
		return buf.toString();
	}

	/**
	 * <p>
	 * Replaces all occurrences of a character in a String with another. This is a null-safe version of
	 * {@link String#replace(char, char)}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> string input returns <code>null</code>. An empty ("") string input returns an empty string.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.replaceChars(null, *, *)        = null
	 * StringUtil.replaceChars("", *, *)          = ""
	 * StringUtil.replaceChars("abcba", 'b', 'y') = "aycya"
	 * StringUtil.replaceChars("abcba", 'z', 'y') = "abcba"
	 * </pre>
	 * 
	 * @param str String to replace characters in, may be null
	 * @param searchChar the character to search for, may be null
	 * @param replaceChar the character to replace, may be null
	 * @return modified String, <code>null</code> if null string input
	 * @since 2.0
	 */
	public static String replaceChars(String str, char searchChar, char replaceChar) {
		if (str == null) {
			return null;
		}
		return str.replace(searchChar, replaceChar);
	}

	/**
	 * <p>
	 * Capitalizes a String changing the first letter to title case as per {@link Character#toTitleCase(char)}. No other
	 * letters are changed.
	 * </p>
	 * 
	 * <p>
	 * For a word based algorithm, see {@link WordUtils#capitalize(String)}. A <code>null</code> input String returns
	 * <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.capitalize(null)  = null
	 * StringUtil.capitalize("")    = ""
	 * StringUtil.capitalize("cat") = "Cat"
	 * StringUtil.capitalize("cAt") = "CAt"
	 * </pre>
	 * 
	 * @param str the String to capitalize, may be null
	 * @return the capitalized String, <code>null</code> if null String input
	 * @see WordUtils#capitalize(String)
	 * @see #uncapitalize(String)
	 * @since 2.0
	 */
	public static String capitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
			.toString();
	}

	/**
	 * <p>
	 * Uncapitalizes a String changing the first letter to title case as per {@link Character#toLowerCase(char)}. No
	 * other letters are changed.
	 * </p>
	 * 
	 * <p>
	 * For a word based algorithm. A <code>null</code> input String returns
	 * <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.uncapitalize(null)  = null
	 * StringUtil.uncapitalize("")    = ""
	 * StringUtil.uncapitalize("Cat") = "cat"
	 * StringUtil.uncapitalize("CAT") = "cAT"
	 * </pre>
	 * 
	 * @param str the String to uncapitalize, may be null
	 * @return the uncapitalized String, <code>null</code> if null String input
	 * @see #capitalize(String)
	 * @since 2.0
	 */
	public static String uncapitalize(String str) {
		int strLen;
		if (str == null || (strLen = str.length()) == 0) {
			return str;
		}
		return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
			.toString();
	}

	// Count matches
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Counts how many times the substring appears in the larger String.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> or empty ("") String input returns <code>0</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.countMatches(null, *)       = 0
	 * StringUtil.countMatches("", *)         = 0
	 * StringUtil.countMatches("abba", null)  = 0
	 * StringUtil.countMatches("abba", "")    = 0
	 * StringUtil.countMatches("abba", "a")   = 2
	 * StringUtil.countMatches("abba", "ab")  = 1
	 * StringUtil.countMatches("abba", "xxx") = 0
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param sub the substring to count, may be null
	 * @return the number of occurrences, 0 if either String is <code>null</code>
	 */
	public static int countMatches(String str, String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = str.indexOf(sub, idx)) != INDEX_NOT_FOUND) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	/**
	 * <p>
	 * Checks if the String contains only unicode letters.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String (length()=0) will return <code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAlpha(null)   = false
	 * StringUtil.isAlpha("")     = true
	 * StringUtil.isAlpha("  ")   = false
	 * StringUtil.isAlpha("abc")  = true
	 * StringUtil.isAlpha("ab2c") = false
	 * StringUtil.isAlpha("ab-c") = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if only contains letters, and is non-null
	 */
	public static boolean isAlpha(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLetter(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only whitespace.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String (length()=0) will return <code>true</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isWhitespace(null)   = false
	 * StringUtil.isWhitespace("")     = true
	 * StringUtil.isWhitespace("  ")   = true
	 * StringUtil.isWhitespace("abc")  = false
	 * StringUtil.isWhitespace("ab2c") = false
	 * StringUtil.isWhitespace("ab-c") = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if only contains whitespace, and is non-null
	 * @since 2.0
	 */
	public static boolean isWhitespace(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if ((Character.isWhitespace(str.charAt(i)) == false)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only lowercase characters.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String (length()=0) will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAllLowerCase(null)   = false
	 * StringUtil.isAllLowerCase("")     = false
	 * StringUtil.isAllLowerCase("  ")   = false
	 * StringUtil.isAllLowerCase("abc")  = true
	 * StringUtil.isAllLowerCase("abC") = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if only contains lowercase characters, and is non-null
	 * @since 2.5
	 */
	public static boolean isAllLowerCase(String str) {
		if (str == null || isEmpty(str)) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isLowerCase(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if the String contains only uppercase characters.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code> will return <code>false</code>. An empty String (length()=0) will return <code>false</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.isAllUpperCase(null)   = false
	 * StringUtil.isAllUpperCase("")     = false
	 * StringUtil.isAllUpperCase("  ")   = false
	 * StringUtil.isAllUpperCase("ABC")  = true
	 * StringUtil.isAllUpperCase("aBC") = false
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @return <code>true</code> if only contains uppercase characters, and is non-null
	 * @since 2.5
	 */
	public static boolean isAllUpperCase(String str) {
		if (str == null || isEmpty(str)) {
			return false;
		}
		int sz = str.length();
		for (int i = 0; i < sz; i++) {
			if (Character.isUpperCase(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	// Reversing
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Reverses a String as per {@link StrBuilder#reverse()}.
	 * </p>
	 * 
	 * <p>
	 * A <code>null</code> String returns <code>null</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.reverse(null)  = null
	 * StringUtil.reverse("")    = ""
	 * StringUtil.reverse("bat") = "tab"
	 * </pre>
	 * 
	 * @param str the String to reverse, may be null
	 * @return the reversed String, <code>null</code> if null String input
	 */
	public static String reverse(String str) {
		if (str == null) {
			return null;
		}
		return new StringBuilder(str).reverse().toString();
	}

	// Abbreviating
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into
	 * "Now is the time for..."
	 * </p>
	 * 
	 * <p>
	 * Specifically:
	 * <ul>
	 * <li>If <code>str</code> is less than <code>maxWidth</code> characters long, return it.</li>
	 * <li>Else abbreviate it to <code>(substring(str, 0, max-3) + "...")</code>.</li>
	 * <li>If <code>maxWidth</code> is less than <code>4</code>, throw an <code>IllegalArgumentException</code>.</li>
	 * <li>In no case will it return a String of length greater than <code>maxWidth</code>.</li>
	 * </ul>
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.abbreviate(null, *)      = null
	 * StringUtil.abbreviate("", 4)        = ""
	 * StringUtil.abbreviate("abcdefg", 6) = "abc..."
	 * StringUtil.abbreviate("abcdefg", 7) = "abcdefg"
	 * StringUtil.abbreviate("abcdefg", 8) = "abcdefg"
	 * StringUtil.abbreviate("abcdefg", 4) = "a..."
	 * StringUtil.abbreviate("abcdefg", 3) = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param maxWidth maximum length of result String, must be at least 4
	 * @return abbreviated String, <code>null</code> if null String input
	 * @throws IllegalArgumentException if the width is too small
	 * @since 2.0
	 */
	public static String abbreviate(String str, int maxWidth) {
		return abbreviate(str, 0, maxWidth);
	}

	/**
	 * <p>
	 * Abbreviates a String using ellipses. This will turn "Now is the time for all good men" into
	 * "...is the time for..."
	 * </p>
	 * 
	 * <p>
	 * Works like <code>abbreviate(String, int)</code>, but allows you to specify a "left edge" offset. Note that this
	 * left edge is not necessarily going to be the leftmost character in the result, or the first character following
	 * the ellipses, but it will appear somewhere in the result.
	 * 
	 * <p>
	 * In no case will it return a String of length greater than <code>maxWidth</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.abbreviate(null, *, *)                = null
	 * StringUtil.abbreviate("", 0, 4)                  = ""
	 * StringUtil.abbreviate("abcdefghijklmno", -1, 10) = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 0, 10)  = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 1, 10)  = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 4, 10)  = "abcdefg..."
	 * StringUtil.abbreviate("abcdefghijklmno", 5, 10)  = "...fghi..."
	 * StringUtil.abbreviate("abcdefghijklmno", 6, 10)  = "...ghij..."
	 * StringUtil.abbreviate("abcdefghijklmno", 8, 10)  = "...ijklmno"
	 * StringUtil.abbreviate("abcdefghijklmno", 10, 10) = "...ijklmno"
	 * StringUtil.abbreviate("abcdefghijklmno", 12, 10) = "...ijklmno"
	 * StringUtil.abbreviate("abcdefghij", 0, 3)        = IllegalArgumentException
	 * StringUtil.abbreviate("abcdefghij", 5, 6)        = IllegalArgumentException
	 * </pre>
	 * 
	 * @param str the String to check, may be null
	 * @param offset left edge of source String
	 * @param maxWidth maximum length of result String, must be at least 4
	 * @return abbreviated String, <code>null</code> if null String input
	 * @throws IllegalArgumentException if the width is too small
	 * @since 2.0
	 */
	public static String abbreviate(String str, int offset, int maxWidth) {
		if (str == null) {
			return null;
		}
		if (maxWidth < 4) {
			throw new IllegalArgumentException("Minimum abbreviation width is 4");
		}
		if (str.length() <= maxWidth) {
			return str;
		}
		if (offset > str.length()) {
			offset = str.length();
		}
		if ((str.length() - offset) < (maxWidth - 3)) {
			offset = str.length() - (maxWidth - 3);
		}
		if (offset <= 4) {
			return str.substring(0, maxWidth - 3) + "...";
		}
		if (maxWidth < 7) {
			throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
		}
		if ((offset + (maxWidth - 3)) < str.length()) {
			return "..." + abbreviate(str.substring(offset), maxWidth - 3);
		}
		return "..." + str.substring(str.length() - (maxWidth - 3));
	}

	// Difference
	//-----------------------------------------------------------------------
	/**
	 * <p>
	 * Compares two Strings, and returns the portion where they differ. (More precisely, return the remainder of the
	 * second String, starting from where it's different from the first.)
	 * </p>
	 * 
	 * <p>
	 * For example, <code>difference("i am a machine", "i am a robot") -> "robot"</code>.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.difference(null, null) = null
	 * StringUtil.difference("", "") = ""
	 * StringUtil.difference("", "abc") = "abc"
	 * StringUtil.difference("abc", "") = ""
	 * StringUtil.difference("abc", "abc") = ""
	 * StringUtil.difference("ab", "abxyz") = "xyz"
	 * StringUtil.difference("abcde", "abxyz") = "xyz"
	 * StringUtil.difference("abcde", "xyz") = "xyz"
	 * </pre>
	 * 
	 * @param str1 the first String, may be null
	 * @param str2 the second String, may be null
	 * @return the portion of str2 where it differs from str1; returns the empty String if they are equal
	 * @since 2.0
	 */
	public static String difference(String str1, String str2) {
		if (str1 == null) {
			return str2;
		}
		if (str2 == null) {
			return str1;
		}
		int at = indexOfDifference(str1, str2);
		if (at == INDEX_NOT_FOUND) {
			return EMPTY;
		}
		return str2.substring(at);
	}

	/**
	 * <p>
	 * Compares two Strings, and returns the index at which the Strings begin to differ.
	 * </p>
	 * 
	 * <p>
	 * For example, <code>indexOfDifference("i am a machine", "i am a robot") -> 7</code>
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.indexOfDifference(null, null) = -1
	 * StringUtil.indexOfDifference("", "") = -1
	 * StringUtil.indexOfDifference("", "abc") = 0
	 * StringUtil.indexOfDifference("abc", "") = 0
	 * StringUtil.indexOfDifference("abc", "abc") = -1
	 * StringUtil.indexOfDifference("ab", "abxyz") = 2
	 * StringUtil.indexOfDifference("abcde", "abxyz") = 2
	 * StringUtil.indexOfDifference("abcde", "xyz") = 0
	 * </pre>
	 * 
	 * @param str1 the first String, may be null
	 * @param str2 the second String, may be null
	 * @return the index where str2 and str1 begin to differ; -1 if they are equal
	 * @since 2.0
	 */
	public static int indexOfDifference(String str1, String str2) {
		if (str1 == str2) {
			return INDEX_NOT_FOUND;
		}
		if (str1 == null || str2 == null) {
			return 0;
		}
		int i;
		for (i = 0; i < str1.length() && i < str2.length(); ++i) {
			if (str1.charAt(i) != str2.charAt(i)) {
				break;
			}
		}
		if (i < str2.length() || i < str1.length()) {
			return i;
		}
		return INDEX_NOT_FOUND;
	}

	// startsWith
	//-----------------------------------------------------------------------

	/**
	 * <p>
	 * Check if a String starts with a specified prefix.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
	 * The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.startsWith(null, null)      = true
	 * StringUtil.startsWith(null, "abc")     = false
	 * StringUtil.startsWith("abcdef", null)  = false
	 * StringUtil.startsWith("abcdef", "abc") = true
	 * StringUtil.startsWith("ABCDEF", "abc") = false
	 * </pre>
	 * 
	 * @see String#startsWith(String)
	 * @param str the String to check, may be null
	 * @param prefix the prefix to find, may be null
	 * @return <code>true</code> if the String starts with the prefix, case sensitive, or both <code>null</code>
	 * @since 2.4
	 */
	public static boolean startsWith(String str, String prefix) {
		return startsWith(str, prefix, false);
	}

	/**
	 * <p>
	 * Case insensitive check if a String starts with a specified prefix.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
	 * The comparison is case insensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.startsWithIgnoreCase(null, null)      = true
	 * StringUtil.startsWithIgnoreCase(null, "abc")     = false
	 * StringUtil.startsWithIgnoreCase("abcdef", null)  = false
	 * StringUtil.startsWithIgnoreCase("abcdef", "abc") = true
	 * StringUtil.startsWithIgnoreCase("ABCDEF", "abc") = true
	 * </pre>
	 * 
	 * @see String#startsWith(String)
	 * @param str the String to check, may be null
	 * @param prefix the prefix to find, may be null
	 * @return <code>true</code> if the String starts with the prefix, case insensitive, or both <code>null</code>
	 * @since 2.4
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		return startsWith(str, prefix, true);
	}

	/**
	 * <p>
	 * Check if a String starts with a specified prefix (optionally case insensitive).
	 * </p>
	 * 
	 * @see String#startsWith(String)
	 * @param str the String to check, may be null
	 * @param prefix the prefix to find, may be null
	 * @param ignoreCase inidicates whether the compare should ignore case (case insensitive) or not.
	 * @return <code>true</code> if the String starts with the prefix or both <code>null</code>
	 */
	private static boolean startsWith(String str, String prefix, boolean ignoreCase) {
		if (str == null || prefix == null) {
			return (str == null && prefix == null);
		}
		if (prefix.length() > str.length()) {
			return false;
		}
		return str.regionMatches(ignoreCase, 0, prefix, 0, prefix.length());
	}

	/**
	 * <p>
	 * Check if a String starts with any of an array of specified strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.startsWithAny(null, null)      = false
	 * StringUtil.startsWithAny(null, new String[] {"abc"})  = false
	 * StringUtil.startsWithAny("abcxyz", null)     = false
	 * StringUtil.startsWithAny("abcxyz", new String[] {""}) = false
	 * StringUtil.startsWithAny("abcxyz", new String[] {"abc"}) = true
	 * StringUtil.startsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
	 * </pre>
	 * 
	 * @see #startsWith(String, String)
	 * @param string the String to check, may be null
	 * @param searchStrings the Strings to find, may be null or empty
	 * @return <code>true</code> if the String starts with any of the the prefixes, case insensitive, or both
	 *         <code>null</code>
	 * @since 2.5
	 */
	public static boolean startsWithAny(String string, String[] searchStrings) {
		if (isEmpty(string) || ArrayUtil.isEmpty(searchStrings)) {
			return false;
		}
		for (int i = 0; i < searchStrings.length; i++) {
			String searchString = searchStrings[i];
			if (StringUtil.startsWith(string, searchString)) {
				return true;
			}
		}
		return false;
	}

	// endsWith
	//-----------------------------------------------------------------------

	/**
	 * <p>
	 * Check if a String ends with a specified suffix.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
	 * The comparison is case sensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.endsWith(null, null)      = true
	 * StringUtil.endsWith(null, "def")     = false
	 * StringUtil.endsWith("abcdef", null)  = false
	 * StringUtil.endsWith("abcdef", "def") = true
	 * StringUtil.endsWith("ABCDEF", "def") = false
	 * StringUtil.endsWith("ABCDEF", "cde") = false
	 * </pre>
	 * 
	 * @see String#endsWith(String)
	 * @param str the String to check, may be null
	 * @param suffix the suffix to find, may be null
	 * @return <code>true</code> if the String ends with the suffix, case sensitive, or both <code>null</code>
	 * @since 2.4
	 */
	public static boolean endsWith(String str, String suffix) {
		return endsWith(str, suffix, false);
	}

	/**
	 * <p>
	 * Case insensitive check if a String ends with a specified suffix.
	 * </p>
	 * 
	 * <p>
	 * <code>null</code>s are handled without exceptions. Two <code>null</code> references are considered to be equal.
	 * The comparison is case insensitive.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.endsWithIgnoreCase(null, null)      = true
	 * StringUtil.endsWithIgnoreCase(null, "def")     = false
	 * StringUtil.endsWithIgnoreCase("abcdef", null)  = false
	 * StringUtil.endsWithIgnoreCase("abcdef", "def") = true
	 * StringUtil.endsWithIgnoreCase("ABCDEF", "def") = true
	 * StringUtil.endsWithIgnoreCase("ABCDEF", "cde") = false
	 * </pre>
	 * 
	 * @see String#endsWith(String)
	 * @param str the String to check, may be null
	 * @param suffix the suffix to find, may be null
	 * @return <code>true</code> if the String ends with the suffix, case insensitive, or both <code>null</code>
	 * @since 2.4
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		return endsWith(str, suffix, true);
	}

	/**
	 * <p>
	 * Check if a String ends with a specified suffix (optionally case insensitive).
	 * </p>
	 * 
	 * @see String#endsWith(String)
	 * @param str the String to check, may be null
	 * @param suffix the suffix to find, may be null
	 * @param ignoreCase inidicates whether the compare should ignore case (case insensitive) or not.
	 * @return <code>true</code> if the String starts with the prefix or both <code>null</code>
	 */
	private static boolean endsWith(String str, String suffix, boolean ignoreCase) {
		if (str == null || suffix == null) {
			return (str == null && suffix == null);
		}
		if (suffix.length() > str.length()) {
			return false;
		}
		int strOffset = str.length() - suffix.length();
		return str.regionMatches(ignoreCase, strOffset, suffix, 0, suffix.length());
	}

	/**
	 * <p>
	 * Check if a String ends with any of an array of specified strings.
	 * </p>
	 * 
	 * <pre>
	 * StringUtil.endsWithAny(null, null)      = false
	 * StringUtil.endsWithAny(null, new String[] {"abc"})  = false
	 * StringUtil.endsWithAny("abcxyz", null)     = false
	 * StringUtil.endsWithAny("abcxyz", new String[] {""}) = true
	 * StringUtil.endsWithAny("abcxyz", new String[] {"xyz"}) = true
	 * StringUtil.endsWithAny("abcxyz", new String[] {null, "xyz", "abc"}) = true
	 * </pre>
	 * 
	 * @param string the String to check, may be null
	 * @param searchStrings the Strings to find, may be null or empty
	 * @return <code>true</code> if the String ends with any of the the prefixes, case insensitive, or both
	 *         <code>null</code>
	 * @since 2.6
	 */
	public static boolean endsWithAny(String string, String[] searchStrings) {
		if (isEmpty(string) || ArrayUtil.isEmpty(searchStrings)) {
			return false;
		}
		for (int i = 0; i < searchStrings.length; i++) {
			String searchString = searchStrings[i];
			if (StringUtil.endsWith(string, searchString)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isLetter(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	/**
	 * 转为驼峰字符�?<li>Utils.toCameCase("ab-cd-ef") = "abCdEf"</li> <li>Utils.toCameCase("_ab_cD-ef") = "abCDEf"</li>
	 */
	public static String toCameCase(String s) {
		StringBuilder sb = new StringBuilder();
		// 逐个查看字符串的每个字符，若不是字母则标记一下，下一个字母大�?
		boolean upper = false;
		for (int i = 0; i < s.length(); i++) {
			if (!isLetter(s.charAt(i))) {
				upper = true;
				continue;
			}

			// 结果的第�?��字母小写
			if (sb.length() == 0) {

				sb.append(String.valueOf(s.charAt(i)).toLowerCase());
				upper = false;
				continue;
			}

			if (upper) {
				sb.append(String.valueOf(s.charAt(i)).toUpperCase());
				upper = false;
				continue;
			}

			sb.append(s.charAt(i));
		}

		return sb.toString();
	}

	/***
	 * 根据�?��“简体汉字的字符串�? 返回�?�� “汉字拼音首字母(大写)”组成的字符�?例如 “北京无线天利�? 返回 "BJWXTL"
	 * 
	 * @param chineseString 中文汉字字符�?
	 */
	public static String chinese2Alpha(final String chineseString) {
		final StringBuilder alphaBuf = new StringBuilder();
		if (!StringUtil.isBlank(chineseString)) {
			for (int i = 0, len = chineseString.length(); i < len; i++) {
				alphaBuf.append(char2Alpha(chineseString.charAt(i)));
			}
		}
		return alphaBuf.toString();
	}

	/**
	 * @param ch 汉字字符
	 * @return 输入的简体汉�?得到他的声母, // 英文字母返回对应的大写字�?// 其他非简体汉字返�?'0'
	 */
	public static char char2Alpha(final char ch) {
		final char zero = '0';
		if (ch >= 'a' && ch <= 'z')
			return (char) (ch - 'a' + 'A');
		if (ch >= 'A' && ch <= 'Z')
			return ch;
		int gb = gbCode(ch);
		if (gb < table[0])
			return zero;
		int i;
		for (i = 0; i < 26; ++i) {
			if (match(i, gb))
				break;
		}
		if (i >= 26)
			return zero;
		else
			return alphatable[i];
	}

	private static boolean match(int index, int gb) {
		if (gb < table[index])
			return false;
		int j = index + 1;
		// 字母Z使用了两个标�?
		while (j < 26 && (table[j] == table[index]))
			++j;
		if (j == 26)
			return gb <= table[j];
		else
			return gb < table[j];
	}

	// 取出汉字的编�?
	private static int gbCode(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Performs the logic for the <code>split</code> and <code>splitPreserveAllTokens</code> methods that return a
	 * maximum array length.
	 * 
	 * @param str the String to parse, may be <code>null</code>
	 * @param separatorChars the separate character
	 * @param max the maximum number of elements to include in the array. A zero or negative value implies no limit.
	 * @param preserveAllTokens if <code>true</code>, adjacent separators are treated as empty token separators; if
	 *        <code>false</code>, adjacent separators are treated as one separator.
	 * @return an array of parsed Strings, <code>null</code> if null String input
	 */
	private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
		// Performance tuned for 2.0 (JDK1.4)
		// Direct code is quicker than StringTokenizer.
		// Also, StringTokenizer uses isSpace() not isWhitespace()

		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		List<String> list = new ArrayList<String>();
		int sizePlus1 = 1;
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		if (separatorChars == null) {
			// Null separator means use whitespace
			while (i < len) {
				if (Character.isWhitespace(str.charAt(i))) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else if (separatorChars.length() == 1) {
			// Optimise 1 character case
			char sep = separatorChars.charAt(0);
			while (i < len) {
				if (str.charAt(i) == sep) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		} else {
			// standard case
			while (i < len) {
				if (separatorChars.indexOf(str.charAt(i)) >= 0) {
					if (match || preserveAllTokens) {
						lastMatch = true;
						if (sizePlus1++ == max) {
							i = len;
							lastMatch = false;
						}
						list.add(str.substring(start, i));
						match = false;
					}
					start = ++i;
					continue;
				}
				lastMatch = false;
				match = true;
				i++;
			}
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

	/**
	 * Performs the logic for the <code>split</code> and <code>splitPreserveAllTokens</code> methods that do not return
	 * a maximum array length.
	 * 
	 * @param str the String to parse, may be <code>null</code>
	 * @param separatorChar the separate character
	 * @param preserveAllTokens if <code>true</code>, adjacent separators are treated as empty token separators; if
	 *        <code>false</code>, adjacent separators are treated as one separator.
	 * @return an array of parsed Strings, <code>null</code> if null String input
	 */
	private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
		// Performance tuned for 2.0 (JDK1.4)

		if (str == null) {
			return null;
		}
		int len = str.length();
		if (len == 0) {
			return ArrayUtil.EMPTY_STRING_ARRAY;
		}
		List<String> list = new ArrayList<String>();
		int i = 0, start = 0;
		boolean match = false;
		boolean lastMatch = false;
		while (i < len) {
			if (str.charAt(i) == separatorChar) {
				if (match || preserveAllTokens) {
					list.add(str.substring(start, i));
					match = false;
					lastMatch = true;
				}
				start = ++i;
				continue;
			}
			lastMatch = false;
			match = true;
			i++;
		}
		if (match || (preserveAllTokens && lastMatch)) {
			list.add(str.substring(start, i));
		}
		return (String[]) list.toArray(new String[list.size()]);
	}

}
