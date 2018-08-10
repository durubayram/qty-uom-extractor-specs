package com.omnius.challenges.extractors.qtyuom.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.StringJoiner;
import com.omnius.challenges.extractors.qtyuom.QtyUomExtractor;
import com.omnius.challenges.extractors.qtyuom.utils.Pair;




/**
 * Implements {@link QtyUomExtractor} identifying as <strong>the most relevant UOM</strong> the leftmost UOM found in the articleDescription.
 * The {@link UOM} array contains the list of valid UOMs. The algorithm search for the leftmost occurence of UOM[i], if there are no occurrences then tries UOM[i+1].
 * 
 * Example
 * <ul>
 * <li>article description: "black steel bar 35 mm 77 stck"</li>
 * <li>QTY: "77" (and NOT "35")</li>
 * <li>UOM: "stck" (and not "mm" since "stck" has an higher priority as UOM )</li>
 * </ul>
 *
 * @author <a href="mailto:damiano@searchink.com">Damiano Giampaoli</a>
 * @since 4 May 2018
 */
public class LeftMostUOMExtractor implements QtyUomExtractor {

    /**
     * Array of valid UOM to match. the elements with lower index in the array has higher priority
     */
    public static String[] UOM = {//"stk",
            "stk.", "stck", "stück", //"stg",
            "stg.", // "st",
            "st.", "stange", "stange(n)", "tafel", "tfl", "taf", "mtr", "meter", "qm", "kg", "lfm", "mm", "m"};



    public LeftMostUOMExtractor() {}
    
    @Override
    public Pair<String, String> extract(String articleDescription) {

        if (articleDescription == null || articleDescription.trim().isEmpty())
        {
            return null;
        }

        String quantity = null;
        String unit = null;
        String pattern = "(((\\s((\\d{1,3})?)((\\s|,)\\d{3})*)|(\\d+))(\\ ?(\\.|,)\\ ?\\d+)?)\\ " + '(' + String.join("|", UOM)
                .replace("(n)","\\(n\\)").replace(".", "\\.?") + ")\\s";

        Pattern r = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        Matcher m = r.matcher(articleDescription);

        if(m.find())
        {
            quantity=m.group(1).replace(" ","");
            unit=m.group(11).toLowerCase();
        } else {
            return null;
        }

        return new Pair<String, String>(quantity.trim(), unit);
    }

    @Override
    public Pair<Double, String> extractAsDouble(String articleDescription) {
        //mock implementation
        return new Pair<Double, String>(34.5d,"m");
    }


}
