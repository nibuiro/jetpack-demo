package com.example.svgsting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.compose.ui.tooling.preview.Preview
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
//https://github.com/android/compose-samples/search?q=setContent
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.graphics.vector.VectorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
//import com.example.svgsting.utilities.VectorDrawableCreator.PathData
//import com.example.svgsting.utilities.VectorDrawableCreator.createBinaryDrawableXml
import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import com.example.svgsting.customCompose.loadVectorXmlResource

import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FixedScale

fun parsePath(pathStr: String) = PathParser().parsePathString(pathStr).toNodes()

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SvgStory();
        }
    }
}

@Composable
fun SvgStory() {
    val context = LocalContext.current
    val density = LocalDensity.current
    val vector_xml = """<svg
    xmlns="http://www.w3.org/2000/svg"
   viewBox="0 0 117.18 37.28">
   
<title>Android Studio logo</title>
  <path
     d="M5.26,5.38A4.35,4.35,0,0,1,8.74,7.05V5.59h2.4V16.51H9.51a.76.76,0,0,1-.77-.77h0V15a4.31,4.31,0,0,1-3.48,1.68A5.42,5.42,0,0,1,0,11.14.34.34,0,0,1,0,11,5.42,5.42,0,0,1,5.17,5.37h.09m.39,2.18a3.27,3.27,0,0,0-3.26,3.28,1.62,1.62,0,0,0,0,.22,3.26,3.26,0,1,0,6.5.51,4.34,4.34,0,0,0,0-.51,3.28,3.28,0,0,0-3-3.49H5.65m8.17-2h2.4V7a3.75,3.75,0,0,1,3.31-1.7c2.53,0,4.17,1.79,4.17,4.56v6.57H22.11a.75.75,0,0,1-.77-.73V10.33c0-1.73-.87-2.78-2.27-2.77-1.59,0-2.81,1.24-2.81,3.58v5.37H14.65a.78.78,0,0,1-.77-.77Zm17-.21A4.35,4.35,0,0,1,34.3,7V.14h2.39V16.51H35.14a.75.75,0,0,1-.77-.75V15a4.34,4.34,0,0,1-3.49,1.68,5.44,5.44,0,0,1-5.24-5.62V11a5.43,5.43,0,0,1,5.18-5.67h.07m.44,2.18a3.27,3.27,0,0,0-3.26,3.28,1.62,1.62,0,0,0,0,.22,3.26,3.26,0,1,0,6.5.51,4.34,4.34,0,0,0,0-.51,3.28,3.28,0,0,0-3-3.49h-.21m8.17-2h2.4v2a3,3,0,0,1,2.87-2.06,5,5,0,0,1,.94.09V8a3.82,3.82,0,0,0-1.2-.2c-1.37,0-2.61,1.16-2.61,3.34v5.33H40.26a.75.75,0,0,1-.77-.75v0ZM52.16,16.73a5.65,5.65,0,1,1,.13,0h-.13m0-2.23a3.27,3.27,0,0,0,3.27-3.27v-.18a3.28,3.28,0,0,0-6.56,0A3.27,3.27,0,0,0,52,14.5h.21M61,3.22a1.61,1.61,0,1,1,1.63-1.59v0a1.63,1.63,0,0,1-1.6,1.61m-1.2,2.37h2.4V16.51H60.59a.75.75,0,0,1-.55-.23.77.77,0,0,1-.22-.54Zm9.62-.21A4.35,4.35,0,0,1,73,7.09V.14h2.4V16.51h-1.7a.76.76,0,0,1-.77-.77V15a4.31,4.31,0,0,1-3.48,1.68,5.43,5.43,0,0,1-5.27-5.59s0-.06,0-.09a5.43,5.43,0,0,1,5.18-5.67h.08m.43,2.18a3.27,3.27,0,0,0-3.19,3.35.76.76,0,0,0,0,.15,3.26,3.26,0,1,0,6.5.51,4.34,4.34,0,0,0,0-.51,3.28,3.28,0,0,0-3-3.49H69.9M26.24,33.48V28.16H24V26h2.29V22.94h2.41V26H31.8v2.16H28.65v4.92c0,1.39.6,1.87,1.85,1.87a3.24,3.24,0,0,0,1.3-.22v2.16a5.8,5.8,0,0,1-1.8.25,3.43,3.43,0,0,1-3.76-3.07A2.91,2.91,0,0,1,26.24,33.48Zm37.55-2a5.77,5.77,0,1,1,5.77,5.77h0a5.69,5.69,0,0,1-5.76-5.62v-.15Zm9.07,0a3.4,3.4,0,1,0,0,.18v-.18Zm-56,2.17a2,2,0,0,0,2.27,1.63c1.08,0,1.79-.44,1.79-1.17s-.48-1.17-1.63-1.46l-1.81-.48a3.31,3.31,0,0,1-2.82-3.33c0-1.85,1.63-3.09,4.1-3.09s4,1.3,4.21,3.31H20.58a1.76,1.76,0,0,0-1.81-1.37c-.95,0-1.61.42-1.61,1.12s.49,1.08,1.49,1.34l1.85.49c2,.51,2.94,1.61,2.94,3.33,0,2.1-1.76,3.26-4.32,3.26a5.52,5.52,0,0,1-3.31-1,3.28,3.28,0,0,1-1.43-2.58ZM40.82,35.5a3.62,3.62,0,0,1-3.17,1.74c-2.53,0-4.1-1.83-4.1-4.65V26H36v6.19C36,34,36.77,35,38.18,35c1.61,0,2.69-1.32,2.69-3.61V26h2.42V37H40.82Zm13.27,0a4.4,4.4,0,0,1-3.44,1.71,5.41,5.41,0,0,1-5.31-5.51.76.76,0,0,1,0-.15,5.39,5.39,0,0,1,5.12-5.66h.18a4.4,4.4,0,0,1,3.49,1.68V20.65h2.39V37H54.09ZM48.65,34A3,3,0,0,0,51,35a2.94,2.94,0,0,0,2.33-1,3.85,3.85,0,0,0,0-5A3,3,0,0,0,51,28a3,3,0,0,0-2.36,1A3.9,3.9,0,0,0,48.65,34ZM60.4,20.47a1.61,1.61,0,0,1,0,3.21A1.63,1.63,0,0,1,59,22.89a1.59,1.59,0,0,1,0-1.63A1.63,1.63,0,0,1,60.4,20.47Zm1.25,5.58V37h-2.4V26.05Z"
     fill="#000000"
     id="path2" />
  <path
     d="M98,4.71H89.55a3.1,3.1,0,0,0-3.09,3.1,3,3,0,0,0,2.91,3.09h8.69Z"
     fill="#073042"
     id="path4" />
  <path
     d="M117.15,32.26H89.54a3.1,3.1,0,0,1-3.08-3.09V7.79a3.1,3.1,0,0,0,3.08,3.12h23.11s4.5-.39,4.5,3Z"
     fill="#4285f4"
     id="path6" />
  <path
     d="M104.27,21.31a2.78,2.78,0,0,0-1.59-5h.09a2,2,0,0,1,.49,0V14.59a.56.56,0,0,0-.26-.49.54.54,0,0,0-.81.49v1.75a2.78,2.78,0,0,0-2.32,3.19,2.75,2.75,0,0,0,1.1,1.81L95.88,32.22H99.2l2.35-5a1.21,1.21,0,0,1,2.19,0l2.42,5h3.36Zm-1.7-.49a1.72,1.72,0,0,1-1.71-1.73,1.72,1.72,0,0,1,2.91-1.22,1.72,1.72,0,0,1-1.2,2.95Z"
     fill="#3870b2"
     id="path10" />
  <path
     d="M96.15,6.23H108V8.4H96.15Z"
     fill="#fff"
     id="path12" />
  <path
     d="M101.65,17.39a1.71,1.71,0,1,1-1.73,1.7,1.75,1.75,0,0,1,.51-1.2A1.72,1.72,0,0,1,101.65,17.39Zm.68-1v-1.8a.56.56,0,0,0-.26-.49.54.54,0,0,0-.81.49v1.75a2.78,2.78,0,0,0-2.32,3.19,2.75,2.75,0,0,0,1.1,1.81L93.65,35.09a1.5,1.5,0,0,0,.11,1.53,1.51,1.51,0,0,0,2.63-.25l4.26-9.15a1.21,1.21,0,0,1,2.19,0l4.37,9.1A1.51,1.51,0,0,0,109.93,35l-6.58-13.69a2.81,2.81,0,0,0-1-4.91"
     fill="#073042"
     id="path14" />
  <path
     class="cls-5"
     d="M106.25,8.14a.8.8,0,0,1-.82-.78.77.77,0,0,1,.24-.59.81.81,0,0,1,1.14,0,.84.84,0,0,1,.24.56.8.8,0,0,1-.8.8h0m-8.89,0a.8.8,0,0,1-.81-.79.78.78,0,0,1,.24-.58.8.8,0,0,1,1.14-.11A.82.82,0,0,1,98,7.8l-.11.11a.81.81,0,0,1-.57.23m9.18-4.85,1.6-2.78A.34.34,0,0,0,108,.07a.32.32,0,0,0-.39.1L105.94,3a10.08,10.08,0,0,0-8.26,0L96.05.17A.34.34,0,0,0,95.76,0a.37.37,0,0,0-.29.17.34.34,0,0,0,0,.34l1.61,2.78a9.52,9.52,0,0,0-4.92,7.61h19.3a9.52,9.52,0,0,0-4.92-7.61"
     fill="#3ddc84"
     id="path16" />
  <path
     d="M114.38,16.83h-1.27A.16.16,0,0,0,113,17h0V37a.16.16,0,0,0,.15.16h1.31a2.78,2.78,0,0,0,2.77-2.78V14.05a2.78,2.78,0,0,1-2.78,2.78Z"
     fill="#073042"
     id="path18" />
</svg>"""
    val vector_img = loadVectorXmlResource(
        vector_xml,
        density
    )

    Column(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ) {

        ZoomableImage(vector_img)

        Text("A day in Shark Fin Cove")
        Text("Davenport, California")
        Text("December 2018")
    }
}

@Composable
fun ZoomableImage(svg :ImageVector) {
    val scale = remember { mutableStateOf(1f) }
    val rotationState = remember { mutableStateOf(1f) }
    Box(
        modifier = Modifier
            .clip(RectangleShape) // Clip the box content
            .fillMaxSize() // Give the size you want...
            .background(Color.Gray)
            .pointerInput(Unit) {
                detectTransformGestures { centroid, pan, zoom, rotation ->
                    scale.value *= zoom
                    rotationState.value += rotation
                }
            }
    ) {
        Image(
            imageVector = svg,
            modifier = Modifier
                .align(Alignment.Center) // keep the image centralized into the Box
                .graphicsLayer(
                    // adding some zoom limits (min 50%, max 200%)
                    scaleX = maxOf(.5f, minOf(3f, scale.value)),
                    scaleY = maxOf(.5f, minOf(3f, scale.value)),
                    rotationZ = rotationState.value
                ),
            contentDescription = null
        )
    }
}
