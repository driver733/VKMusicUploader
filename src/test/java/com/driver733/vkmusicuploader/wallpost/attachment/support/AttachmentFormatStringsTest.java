/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Mikhail Yakushin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.driver733.vkmusicuploader.wallpost.attachment.support;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Class or Interface description.
 * <p>
 * Additional info
 *
 * @author Mikhail Yakushin (driver733@me.com)
 * @version $Id$
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle StringLiteralsConcatenationCheck (100 lines)
 */
public final class AttachmentFormatStringsTest {

    @Test
    public void object() throws IOException {
        MatcherAssert.assertThat(
            "Failed to form an attachment string from JsonObject",
            new AttachmentFormatStrings(
                new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .fromJson(
                        "{"
                            + "\"owner_id\"  : 1111111,"
                            + "\"id\"        : 1000000,"
                            + "\"artist\"    : \"Test Artist1\","
                            + "\"title\"     : \"Test Title1\","
                            + "\"url\"       : \"http://test1.com\" "
                            + "}",
                        JsonObject.class
                    )
                    .getAsJsonObject()
            ).attachmentStrings(),
            Matchers.containsInAnyOrder(
                "audio1111111_1000000"
            )
        );
    }

    @Test
    public void array() throws IOException {
        MatcherAssert.assertThat(
            "Failed to form an attachment string from JsonArray",
            new AttachmentFormatStrings(
                new GsonBuilder()
                    .setPrettyPrinting()
                    .create()
                    .fromJson(
                        "["
                            + "{"
                            + "\"owner_id\"  : 111111,"
                            + "\"id\"        : 3000000,"
                            + "\"artist\"    : \"Test Artist2\","
                            + "\"title\"     : \"Test Title2\","
                            + "\"url\"       : \"http://test2.com\" "
                            + "},"
                            + "{"
                            + "\"owner_id\"  : 2222222,"
                            + "\"id\"        : 2000000,"
                            + "\"artist\"    : \"Test Artist3\","
                            + "\"title\"     : \"Test Title3\","
                            + "\"url\"       : \"http://test3.com\" "
                            + "}"
                            + "]",
                        JsonArray.class
                    )
                    .getAsJsonArray()
            ).attachmentStrings(),
            Matchers.containsInAnyOrder(
                "audio111111_3000000", "audio2222222_2000000"
            )
        );
    }

}
