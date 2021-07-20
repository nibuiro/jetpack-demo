package com.example.svgsting.customCompose
/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import org.xml.sax.InputSource
import javax.xml.parsers.DocumentBuilderFactory

/**
 * Synchronously load an xml vector image from some [inputSource].
 *
 * In contrast to [vectorXmlResource] this function isn't [Composable]
 *
 * XML Vector Image is came from Android world. See:
 * https://developer.android.com/guide/topics/graphics/vector-drawable-resources
 *
 * On desktop it is fully implemented except there is no resource linking
 * (for example, we can't reference to color defined in another file)
 *
 * SVG files can be converted to XML with Android Studio or with third party tools
 * (search "svg to xml" in Google)
 *
 * @param inputSource input source to load xml resource. Will be closed automatically.
 * @return the decoded vector image associated with the resource
 */
fun loadVectorXmlResource(
    inputSource: String,
    density: Density
): ImageVector = DocumentBuilderFactory
    .newInstance().apply {
        isNamespaceAware = true
    }
    .newDocumentBuilder()
    .parse(inputSource.byteInputStream())
    .documentElement
    .parseVectorRoot(density)