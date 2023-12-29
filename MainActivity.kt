package com.firstapp.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.times
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.firstapp.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt
import kotlin.time.times

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { unitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun unitConverter() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(0.01) }
    val oConversionFactor = remember { mutableStateOf(0.01) }

    fun unitConvert(){
    val inputDoubleValue = inputValue.toDoubleOrNull() ?: 0.0
    val result =(inputDoubleValue*conversionFactor.value/oConversionFactor.value*100.0).roundToInt() / 100.0
    outputValue = result.toString()
}


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally   ){
        Text(text = "Unit Converter", style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Color.Green // Text color of the title
        ))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {inputValue = it ; unitConvert()}, label = { Text(text = "Enter Value")} )
        Spacer(modifier = Modifier.height(16.dp))



        Row {
                //Input Box here
            Box {
                //Input Button here
               Button(onClick = { iExpanded = true }) {
                   Text(text = "$inputUnit")
                   Icon(Icons.Default.ArrowDropDown ,
                       contentDescription ="Arrow Down" )
               }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded =false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = { iExpanded = false; inputUnit = "Centimeters";conversionFactor.value = 0.01; unitConvert()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = { iExpanded = false; inputUnit = "Meters";conversionFactor.value =1.0 ; unitConvert() })
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = { iExpanded = false; inputUnit = "Feet";conversionFactor.value = 0.3048 ; unitConvert() })
                    DropdownMenuItem(text = { Text(text = "Inches") }, onClick = { iExpanded = false; inputUnit = "Inches";conversionFactor.value = 0.0254 ; unitConvert()})

                }
            }
            Spacer(modifier = Modifier.width(16.dp))
           //Output Box here
            Box {
                //Output Button here
                Button(onClick = { oExpanded = true }) {
                    Text(text = "$outputUnit")
                    Icon(Icons.Default.ArrowDropDown ,
                        contentDescription ="Arrow Down" )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded =false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {oExpanded = false; outputUnit = "Centimeters";oConversionFactor.value = 0.01; unitConvert()})
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = { oExpanded = false; outputUnit = "Meters";oConversionFactor.value = 1.0 ;  unitConvert()})
                    DropdownMenuItem(text = { Text(text = "Feets") }, onClick = { oExpanded = false; outputUnit = "Feet";oConversionFactor.value = 0.3048; unitConvert() })
                    DropdownMenuItem(text = { Text(text = "Inches") }, onClick = { oExpanded = false; outputUnit = "Inches";oConversionFactor.value = 0.0254 ; unitConvert() })

                }
            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        //Result Text
        Text(text = "Result : ${outputValue} $outputUnit", style = MaterialTheme.typography.headlineSmall)


    }


}
 


@Preview(showBackground = true)
@Composable
fun unitConverterPreview(){
    unitConverter()
}
