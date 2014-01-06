/*
 * Copyright 2013 - 2014 Eveoh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package nl.eveoh.mytimetable.apiclient.exception;

/**
 * A RuntimeException that can be internationalised.
 *
 * @author Marco Krikke
 */
public class LocalizableException extends RuntimeException {
    /**
     * Key as used in the resource bundle.
     *
     * Defaults to <tt>error</tt>.
     */
    private String resourceBundleKey = "error";

    /**
     * @see RuntimeException#RuntimeException(String)
     */
    public LocalizableException(String message) {
        super(message);
    }

    /**
     * @param resourceBundleKey Key used for translations.
     *
     * @see RuntimeException#RuntimeException(String)
     */
    public LocalizableException(String message, String resourceBundleKey) {
        super(message);
        this.resourceBundleKey = resourceBundleKey;
    }

    /**
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public LocalizableException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param resourceBundleKey Key used for translations.
     *
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public LocalizableException(String message, Throwable cause, String resourceBundleKey) {
        super(message, cause);
        this.resourceBundleKey = resourceBundleKey;
    }

    public String getResourceBundleKey() {
        return resourceBundleKey;
    }

    public void setResourceBundleKey(String resourceBundleKey) {
        this.resourceBundleKey = resourceBundleKey;
    }
}