package com.omnius.challenges.extractors.qtyuom;

import com.omnius.challenges.extractors.qtyuom.utils.Pair;

/**
 * Detect and extracts a Pair of Quantity(QTY) and Unit Of Measure(UOM) for an article from a free text representing an order issued by a client.
 * The article is supposed to be stored in a warehouse and the client can order it specifying its dimension.
 * 
 * Although the description can contain more than one valid Pair the methods of this class always returns one Pair, the one which looks more relevant under certain heuristics.
 * 
 * Once the UOM is identified the QTY is the number to its left. Only whitespaces can separate QTY and UOM, if other character are in the middle the Pair is not valid.
 * 
 * The number representing the QTY can be formatted as follows:
 * 
 * <ul>
 *  <li>Can be an integer or a real number</li>
 *  <li>Can have Thousand separator or not. If yes it can be a single space or a comma.</li>
 *  <li>Can have a dot or a comma as decimal separator (or nothing in case it is an Integer)</li>
 *  <li>The decimal separator can be within 2 spaces</li>
 *  <li>Of course it can't have the comma both as decimal and thousand separator</li>
 * </ul>
 * 
 * Example:
 * 
 * 1 888 888,7 -> Valid
 * 12 888 888 -> Valid
 * 2431342424,78 -> Valid
 * 544235345 . 54354 -> Valid
 * 32,233,655,899898 -> NOT Valid
 * 32,233,655.899898 -> valid
 *
 * @author <a href="mailto:damiano@omnius.com">Damiano Giampaoli</a>
 * @since 4 May 2018
 */
public interface QtyUomExtractor {

    public static String[] DECIMAL_SEPARATOR = {".", ","};
    public static String[] THOUSAND_SEPARATOR = {" ", ","};
    
    /**
     * Returns the most relevant Pair of QTY and UOM detected in the article description.
     * The definition of "most relevant" is delegated to the implementation.
     * 
     * @param articleDescription The description of the order for that article where to extract the QTY and UOM
     * @return The QTY and UOM representing the dimension of an order for a certain article in a warehouse from a Client.
     */
    public Pair<String, String> extract(String articleDescription);
    
    /**
     * Same as {@link #extract(String)} but returns the QTY as Double instead of String
     * 
     * @param articleDescription @see {@link #extract(String)}
     * @return @see {@link #extract(String)}
     */
    public Pair<Double, String> extractAsDouble(String articleDescription);
}