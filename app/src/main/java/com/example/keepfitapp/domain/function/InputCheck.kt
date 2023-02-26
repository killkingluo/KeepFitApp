package com.example.keepfitapp.domain.function


fun inputCheck(text: String, regex: String): Boolean {
    if(text == "") {
        return true //输入为空
    }
    else if(Regex(pattern = regex).matches(input = text)) {
        return false //输入正确
    }
    return true  //输入错误
}