/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Mikhail Yakushin
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
package com.driver733.vkuploader.wallpost.attachment;

import com.driver733.vkuploader.wallpost.attachment.upload.UploadWallPhoto;
import com.jcabi.aspects.Immutable;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.TransportClientCached;
import com.vk.api.sdk.queries.photos.PhotosSaveWallPhotoQuery;
import java.io.File;
import org.assertj.core.api.Assertions;
import org.cactoos.io.BytesOf;
import org.junit.Test;

/**
 * Test for {@link AttachmentWallPhoto}.
 *
 * @since 0.1
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (20 lines)
 */
@Immutable
public final class AttachmentWallPhotoTest {

    @Test
    @SuppressWarnings("PMD.AvoidDuplicateLiterals")
    public void test() throws Exception {
        Assertions.assertThat(
            new AttachmentWallPhoto(
                new VkApiClient(
                    new TransportClientCached("1")
                ),
                new UserActor(0, ""),
                1,
                new UploadWallPhoto(
                    new VkApiClient(
                        new TransportClientCached(
                            String.join(
                                "",
                                "{",
                                    "\"photo\" : \"testPhoto\",",
                                    "\"server\" : 1,",
                                    "\"hash\" : \"testHash\"",
                                    "}"
                            )
                        )
                    ),
                    "",
                    new BytesOf(
                        new File(
                            "src/test/resources/album/albumCover.jpg"
                        )
                    )
                )
            ).upload()
            .get(0)
            .build()
        ).isEqualTo(
            new PhotosSaveWallPhotoQuery(
                new VkApiClient(
                    new TransportClientCached(
                        String.join(
                            "",
                            "{",
                            "\"photo\" : \"testPhoto\",",
                            "\"server\" : 1,",
                            "\"hash\" : \"testHash\"",
                            "}"
                        )
                    )
                ),
                new UserActor(0, ""),
                "testPhoto"
            ).hash("testHash")
                .groupId(1)
                .server(1)
                .build()
        );
    }

}
