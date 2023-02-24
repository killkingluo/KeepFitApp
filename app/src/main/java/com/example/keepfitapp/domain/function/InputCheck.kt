package com.example.keepfitapp.domain.function

fun inputCheck(text: String, regex: String): Int {
    if(text == "") {
        return 1 //输入为空
    }
    else if(Regex(pattern = regex).matches(input = text)) {
        return 0 //输入正确
    }
    return 2  //输入错误
}