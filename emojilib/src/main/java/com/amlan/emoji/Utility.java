package com.amlan.emoji;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by amlan on 25/5/16.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Utility {

    public static String encodeToUnicode(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();

        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if ((int) c > 127) {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public static String decodeFromUnicode(String s) {
        Pattern pattern = Pattern.compile("\\\\\\\\u([a-fA-F0-9]{4})");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            String match = matcher.group();
            char c = (char) Integer.parseInt(
                    match.substring(3, match.length()), 16);
            s = s.replace(match, "" + c);
        }
        return s;
    }
}
