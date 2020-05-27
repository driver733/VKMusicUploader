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
package com.driver733.vkuploader.wallpost.attachment.mp3filefromfile.advancedtag;

import com.jcabi.aspects.Immutable;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Test for {@link AdvancedTagVerifiedAlbumImage}.
 *
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle IndentationCheck (500 lines)
 * @since 0.1
 */
@Immutable
public final class AdvancedTagVerifiedAlbumImageTest {

    /**
     * Test album cover image.
     */
    private final Path image =
        Paths.get("src/test/resources/album/albumCover.jpg");

    @Test
    public void valid()
        throws InvalidDataException, IOException, UnsupportedTagException {
        final Path path = Paths.get("src/test/resources/album/test.mp3");
        Assertions.assertThat(
            new AdvancedTagVerifiedAlbumImage(
                new AdvancedTagFromMp3File(
                    new Mp3File(
                        path.toFile()
                    )
                )
            ).construct().getAlbumImage()
        ).isEqualTo(
            Files.readAllBytes(this.image)
        );
    }

    @Test(expected = IOException.class)
    public void invalid()
        throws IOException, InvalidDataException, UnsupportedTagException {
        Assertions.assertThat(
            new AdvancedTagVerifiedAlbumImage(
                new AdvancedTagFromMp3File(
                    new Mp3File(
                        "src/test/resources/album/testMissingTags.mp3"
                    )
                )
            ).construct().getAlbumImage()
        ).isEqualTo(
            Files.readAllBytes(this.image)
        );
    }

}
