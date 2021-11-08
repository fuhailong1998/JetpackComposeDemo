package com.fxkxb.jetpackcomposedemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fxkxb.jetpackcomposedemo.ui.theme.JetpackComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}
@Composable
fun MyApp(names: List<String> = listOf("Android", "Thundersoft")) {
    // 组件上下留有4dp内边距
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
private fun Greeting(name: String) {
    // 使用 mutableStateOf 函数，使 Compose 重组 读取该State.
    var expanded = remember {
        mutableStateOf(false);
    }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        // 每行间距4dp
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        // 每行内边距24dp
        Row(modifier = Modifier.padding(24.dp)) {

            Column(modifier = Modifier
                // 充满全屏
                .fillMaxWidth()
                // weight修饰符使元素填充所有可用空间，使其flexible
                // 有效地推开其他没有权重的inflexible元素。
                // 使 fillMaxWidth 修饰符无效。
                .weight(1f)
                // 底部延伸出像素
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello,")
                Text(text = name)
            }

            OutlinedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value)"Show less" else "Show more")
            }
            
        }
    }
}
// 设置常用宽度
@Preview(showBackground = true, widthDp = 320)
@Composable
private fun DefaultPreview() {
    JetpackComposeDemoTheme {
        MyApp()
    }
}