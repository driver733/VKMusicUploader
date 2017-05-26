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

package com.biboran.vkmusicuploader.wallpost;

import com.biboran.vkmusicuploader.wallpost.attachment.AttachmentArrays;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.queries.wall.WallPostQuery;

/**
 * Class or Interface description.
 * <p>
 * Additional info
 *
 * @author Mikhail Yakushin (driver733@me.com)
 * @version $Id$
 * @since 0.1
 */
public final class WallPostWithAttachments implements WallPost {

    /**
     * Origin.
     */
    private final WallPost post;

    /**
     * Attachments.
     */
    private final AttachmentArrays attachments;

    /**
     * Ctor.
     * @param post Origin.
     * @param attachments Attachment arrays.
     */
    public WallPostWithAttachments(
        final WallPost post,
        final AttachmentArrays attachments
    ) {
        this.attachments = attachments;
        this.post = post;
    }

    /**
     * Constructs a WallPost with the provided attachmentsFields.
     * @return Constructed WallPost.
     */
    public WallPostQuery construct() {
        try {
            return this.post.construct()
                .attachments(
                    this.attachments.attachmentsFields()
                );
        } catch (final ClientException | ApiException exception) {
            throw new IllegalStateException(exception);
        }
    }

}
