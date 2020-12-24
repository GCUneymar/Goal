package com.magicPocket.android


import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sunnyweather.android.R
import kotlinx.android.synthetic.main.activity_cal.*

import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Pattern



class CalActivity : AppCompatActivity(),View.OnClickListener {

    //输入的文本内容
    private var InputTextContent = StringBuilder("0")

    //显示结果的文本内容
    private val resultTextContent = java.lang.StringBuilder("=")

    //输入内容的文本对象，下面统称为【输入文本框】
    var inputText: TextView? = null

    //计算结果的文本对象，下面统称为【计算结果文本框】
    private var resultText: TextView? = null

    private val PRE = "(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)" //数字校验正则表达式

    private val PRE_SYMBOL = "[\\+\\-\\×\\÷\\%]" //运算符匹配表达式

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cal)

         inputText  =findViewById(R.id.et);
         resultText = findViewById(R.id.te);

        clear.setOnClickListener(this)
        back.setOnClickListener(this)
        per.setOnClickListener(this)
        chu.setOnClickListener(this)
        bt_7.setOnClickListener(this)
        bt_8.setOnClickListener(this)
        bt_9.setOnClickListener(this)
        cheng.setOnClickListener(this)
        bt_4.setOnClickListener(this)
        bt_5.setOnClickListener(this)
        bt_6.setOnClickListener(this)
        jian.setOnClickListener(this)
        bt_1.setOnClickListener(this)
        bt_2.setOnClickListener(this)
        bt_3.setOnClickListener(this)
        jia.setOnClickListener(this)
        bt_0.setOnClickListener(this)
        dian.setOnClickListener(this)
        deng.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.clear->{//清除AC
            optionC()
        }
            R.id.back ->{//回退BACK
                back()

            }
            R.id.bt_0 -> {
                addAndCalc("0")
            }
            R.id.bt_1 -> {
                addAndCalc("1")
            }
            R.id.bt_2 -> {
                addAndCalc("2")
            }
            R.id.bt_3 -> {
                addAndCalc("3")
            }
            R.id.bt_4 -> {
                addAndCalc("4")
            }
            R.id.bt_5 -> {
                addAndCalc("5")
            }
            R.id.bt_6 -> {
                addAndCalc("6")
            }
            R.id.bt_7 -> {
                addAndCalc("7")
            }
            R.id.bt_8 -> {
                addAndCalc("8")
            }
            R.id.bt_9 -> {
                addAndCalc("9")
            }
            R.id.dian -> {
                addAndCalc(".")
            }
            R.id.per -> {
                addAndCalc("%")
            }
            R.id.chu -> {
                addAndCalc("÷")
            }
            R.id.cheng -> {
                addAndCalc("×")
            }
            R.id.jian -> {
                addAndCalc("-")
            }
            R.id.jia -> {
                addAndCalc("+")
            }
            R.id.deng -> {
                clickEqual()
            }


        }
        //添加字符时，要将【输入文本框】字体变大
        inputText!!.textSize = 60F
        inputText!!.text = InputTextContent

    }

    private fun calculation(expre: String): String {
        //分别获取数字和运算符的数组集合
        val sSymbol: List<String> =
            Pattern.compile(PRE).matcher(expre).replaceAll("").split("")
        val sNum =
            expre.split(PRE_SYMBOL.toRegex()).toTypedArray()
        var result = sNum[0]
        for (i in 1 until sNum.size) {
            result = calc(result, sNum[i], sSymbol[i])
        }
        return result
    }

    //计算两个数的结果
    private fun calc(a: String, b: String, symbol: String): String {
        var result = 0.0
        result = when (symbol) {
            "+" -> a.toDouble() + b.toDouble()
            "-" -> a.toDouble() - b.toDouble()
            "×" -> a.toDouble() * b.toDouble()
            "÷" -> a.toDouble() / b.toDouble()
            "%" -> if (b == "" || b === "") //只有一个数就除以100
                a.toDouble() / 100 else  //两个数就取模运算
                a.toDouble() % b.toDouble()
            else -> throw RuntimeException("参数不合法：a=$a,b=$b,symbol=$symbol")
        }
        //如果resutl结果是整数，就去掉.0后缀
        return result.toString()
    }

    private fun optionC() {
        InputTextContent = java.lang.StringBuilder("0") //清空结果，恢复为初始的0
        resultText!!.visibility = View.INVISIBLE //隐藏计算结果文本框
    }

    //回退 清除一个数
    private fun back(): Unit {
        if (InputTextContent.length > 1) { //不止一个字符
            InputTextContent.delete(InputTextContent.length - 1, InputTextContent.length)
            //获取当前输入的字符前面一个字符
            val lastElement =
                InputTextContent.substring(InputTextContent.length - 1, InputTextContent.length)
        } else {
            InputTextContent = java.lang.StringBuilder("0") //删除完后归零
            resultText!!.visibility = View.INVISIBLE //隐藏计算结果文本框
        }
    }

    //点击等号
    private fun clickEqual() {
        if (isCorrect(InputTextContent.toString())) { //表达式能够计算就进行计算并显示结果

           resultText!!.text =  "="+calculation(InputTextContent.toString()) //设置内容
           resultText!!.visibility = View.VISIBLE //设为可见
           resultText!!.textSize = 60f //设置大小
        }
    }


    //每次添加一个元素到字符串，就对该字符串进行计算
    private fun addAndCalc(newNext: String?) {
        //获取当前输入的字符前面一个字符
        val lastElement =
            InputTextContent.substring(InputTextContent.length - 1, InputTextContent.length)
        if (Pattern.compile(PRE_SYMBOL).matcher(newNext).find() //输入的是符号
            &&
            (InputTextContent.length == 1 && InputTextContent.toString() == "0" //是第一次输入
                    ||
                    Pattern.compile(PRE_SYMBOL).matcher(lastElement).find() //输入前的最后一个元素也是符号
                    )
        ) {
            //Nothing...
        } else {
            if (InputTextContent.length == 1 && InputTextContent.toString() == "0")
            {
                //当只有一个数字且为0时，这个0就要被清除掉，从而解决刚开始输入去除0的问题
                InputTextContent = java.lang.StringBuilder("")
            }
            //将新的数字添加到尾部
            InputTextContent.append(newNext)

            //将输入的数据显示到【输入文本框】
            inputText!!.text = InputTextContent

        }
    }
    //验证表达式是否能够进行计算
    private fun isCorrect(expre: String): Boolean {
        var symbols = arrayOf<String?>()
        val nums = expre.split(PRE_SYMBOL.toRegex()).toTypedArray()
        if (Pattern.compile(PRE_SYMBOL).matcher(expre).find()) //字符串中有符号就进行拆分{
        {
            symbols = Pattern.compile(PRE).matcher(expre).replaceAll("")
                .split("".toRegex()).toTypedArray()

        }
        var s=0
        for(i in 0 until symbols.size-1) {
            s=i
        }
        //判断规则就是“符号数组比数字数字少1”
        return s < nums.size

    }
}
