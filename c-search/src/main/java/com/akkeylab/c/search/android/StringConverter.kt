// https://github.com/maho-ya/ConvertBetweenHalfWidthAndFullWidthCharacters/blob/main/app/src/main/java/com/maho_ya/convertbetweenhalfwidthandfullwidthcharacters/StringConverter.kt
package com.akkeylab.c.search.android

class StringConverter() : IStringConverter {
    override fun toHalfWidth(value: String): String {
        return value.toHalfWidthKana()
            .toHalfWidthAlphaNum()
            .toHalfWidthSymbol()
    }

    override fun toFullWidth(value: String): String {
        return value.toFullWidthDakuOnAndDakuTenKana()
            .toFullWidthKana()
            .toFullWidthAlphaNumAndSymbol()
    }

    private fun String.toHalfWidthKana(): String {
        return this.replace(Regex("[\\u30A1-\\u30F6]]")) {
            // カナ
            when (it.value) {
                "ガ" -> "ｶﾞ"
                "ギ" -> "ｷﾞ"
                "グ" -> "ｸﾞ"
                "ゲ" -> "ｹﾞ"
                "ゴ" -> "ｺﾞ"
                "ザ" -> "ｻﾞ"
                "ジ" -> "ｼﾞ"
                "ズ" -> "ｽﾞ"
                "ゼ" -> "ｾﾞ"
                "ゾ" -> "ｿﾞ"
                "ダ" -> "ﾀﾞ"
                "ヂ" -> "ﾁﾞ"
                "ヅ" -> "ﾂﾞ"
                "デ" -> "ﾃﾞ"
                "ド" -> "ﾄﾞ"
                "バ" -> "ﾊﾞ"
                "ビ" -> "ﾋﾞ"
                "ブ" -> "ﾌﾞ"
                "ベ" -> "ﾍﾞ"
                "ボ" -> "ﾎﾞ"
                "パ" -> "ﾊﾟ"
                "ピ" -> "ﾋﾟ"
                "プ" -> "ﾌﾟ"
                "ペ" -> "ﾍﾟ"
                "ポ" -> "ﾎﾟ"
                "ア" -> "ｱ"
                "イ" -> "ｲ"
                "ウ" -> "ｳ"
                "エ" -> "ｴ"
                "オ" -> "ｵ"
                "カ" -> "ｶ"
                "キ" -> "ｷ"
                "ク" -> "ｸ"
                "ケ" -> "ｹ"
                "コ" -> "ｺ"
                "サ" -> "ｻ"
                "シ" -> "ｼ"
                "ス" -> "ｽ"
                "セ" -> "ｾ"
                "ソ" -> "ｿ"
                "タ" -> "ﾀ"
                "チ" -> "ﾁ"
                "ツ" -> "ﾂ"
                "テ" -> "ﾃ"
                "ト" -> "ﾄ"
                "ナ" -> "ﾅ"
                "ニ" -> "ﾆ"
                "ヌ" -> "ﾇ"
                "ネ" -> "ﾈ"
                "ノ" -> "ﾉ"
                "ハ" -> "ﾊ"
                "ヒ" -> "ﾋ"
                "フ" -> "ﾌ"
                "ヘ" -> "ﾍ"
                "ホ" -> "ﾎ"
                "マ" -> "ﾏ"
                "ミ" -> "ﾐ"
                "ム" -> "ﾑ"
                "メ" -> "ﾒ"
                "モ" -> "ﾓ"
                "ヤ" -> "ﾔ"
                "ユ" -> "ﾕ"
                "ヨ" -> "ﾖ"
                "ラ" -> "ﾗ"
                "リ" -> "ﾘ"
                "ル" -> "ﾙ"
                "レ" -> "ﾚ"
                "ロ" -> "ﾛ"
                "ワ" -> "ﾜ"
                "ヲ" -> "ｦ"
                "ン" -> "ﾝ"
                "ァ" -> "ｧ"
                "ィ" -> "ｨ"
                "ゥ" -> "ｩ"
                "ェ" -> "ｪ"
                "ォ" -> "ｫ"
                "ッ" -> "ｯ"
                "ャ" -> "ｬ"
                "ュ" -> "ｭ"
                "ョ" -> "ｮ"
                "ー" -> "ｰ"
                "。" -> "｡"
                "、" -> "､"
                "「" -> "｢"
                "」" -> "｣"
                "・" -> "･"
                "ヰ" -> "ｲ"
                "ヱ" -> "ｴ"
                "ヵ" -> "ｶ"
                "ヶ" -> "ｹ"
                else -> ""
            }
        }
    }

    private fun String.toHalfWidthAlphaNum(): String {
        return this.replace(Regex("[\\uFF10-\\uFF19]|[\\uFF21-\\uFF3A]|[\\uFF41-\\uFF5A]")) {
            when (val target = it.value.codePointAt(0)) {
                in 0xFF10..0xFF19 -> {
                    // 数字
                    Char(target - 0xFF10 + 0x0030).toString()
                }
                in 0xFF21..0xFF3A -> {
                    // アルファベット大文字
                    Char(target - 0xFF21 + 0x0041).toString()
                }
                in 0xFF41..0xFF5A -> {
                    // アルファベット小文字
                    Char(target - 0xFF41 + 0x0061).toString()
                }
                else -> {
                    // 記号
                    when (it.value) {
                        " " -> "　"
                        "!" -> "！"
                        "\"" -> "”"
                        "#" -> "＃"
                        "$" -> "＄"
                        "%" -> "％"
                        "&" -> "＆"
                        "'" -> "’"
                        "(" -> "（"
                        ")" -> "）"
                        "*" -> "＊"
                        "+" -> "＋"
                        "," -> "，"
                        "-" -> "−"
                        "." -> "．"
                        "/" -> "／"
                        ":" -> "："
                        ";" -> "；"
                        "<" -> "＜"
                        "=" -> "＝"
                        ">" -> "＞"
                        "?" -> "？"
                        "@" -> "＠"
                        "[" -> "［"
                        "\\" -> "＼"
                        "]" -> "］"
                        "^" -> "＾"
                        "_" -> "＿"
                        "`" -> "｀"
                        "{" -> "｛"
                        "|" -> "｜"
                        "}" -> "｝"
                        "~" -> "〜"
                        "¥" -> "￥"
                        else -> ""
                    }
                }
            }
        }
    }

    private fun String.toHalfWidthSymbol(): String {
        val pattern = "！”＃＄％＆’（）＊＋，−．／：；＜＝＞？［＼］＾＿｀｛｜｝〜　￥".toCharArray().toList().joinToString("|")
        return this.replace(Regex(pattern)) {
            // 記号
            when (it.value) {
                "　" -> " "
                "！" -> "!"
                "”" -> "\""
                "＃" -> "#"
                "＄" -> "$"
                "％" -> "%"
                "＆" -> "&"
                "’" -> "'"
                "（" -> "("
                "）" -> ")"
                "＊" -> "*"
                "＋" -> "+"
                "，" -> ","
                "−" -> "-"
                "．" -> "."
                "／" -> "/"
                "：" -> ":"
                "；" -> ";"
                "＜" -> "<"
                "＝" -> "="
                "＞" -> ">"
                "？" -> "?"
                "＠" -> "@"
                "［" -> "["
                "＼" -> "\\"
                "］" -> "]"
                "＾" -> "^"
                "＿" -> "_"
                "｀" -> "`"
                "｛" -> "{"
                "｜" -> "|"
                "｝" -> "}"
                "〜" -> "~"
                "￥" -> "¥"
                else -> ""
            }
        }
    }

    private fun String.toFullWidthDakuOnAndDakuTenKana(): String {
        return this.replace(Regex("[\\uFF71-\\uFF84]\\uFF9e|[\\uFF8A-\\uFF8E][\\uFF9e|\\uFF9F]")) {
            // 濁音・濁点付き半角カナ
            when (it.value) {
                "ｶﾞ" -> "ガ"
                "ｷﾞ" -> "ギ"
                "ｸﾞ" -> "グ"
                "ｹﾞ" -> "ゲ"
                "ｺﾞ" -> "ゴ"
                "ｻﾞ" -> "ザ"
                "ｼﾞ" -> "ジ"
                "ｽﾞ" -> "ズ"
                "ｾﾞ" -> "ゼ"
                "ｿﾞ" -> "ゾ"
                "ﾀﾞ" -> "ダ"
                "ﾁﾞ" -> "ヂ"
                "ﾂﾞ" -> "ヅ"
                "ﾃﾞ" -> "デ"
                "ﾄﾞ" -> "ド"
                "ﾊﾞ" -> "バ"
                "ﾋﾞ" -> "ビ"
                "ﾌﾞ" -> "ブ"
                "ﾍﾞ" -> "ベ"
                "ﾎﾞ" -> "ボ"
                "ﾊﾟ" -> "パ"
                "ﾋﾟ" -> "ピ"
                "ﾌﾟ" -> "プ"
                "ﾍﾟ" -> "ペ"
                "ﾎﾟ" -> "ポ"
                else -> ""
            }
        }
    }

    private fun String.toFullWidthKana(): String {
        return this.replace(Regex("[\\uFF61-\\uFF9F]")) {
            // 半角カナ
            when (it.value) {
                "ｱ" -> "ア"
                "ｲ" -> "イ"
                "ｳ" -> "ウ"
                "ｴ" -> "エ"
                "ｵ" -> "オ"
                "ｶ" -> "カ"
                "ｷ" -> "キ"
                "ｸ" -> "ク"
                "ｹ" -> "ケ"
                "ｺ" -> "コ"
                "ｻ" -> "サ"
                "ｼ" -> "シ"
                "ｽ" -> "ス"
                "ｾ" -> "セ"
                "ｿ" -> "ソ"
                "ﾀ" -> "タ"
                "ﾁ" -> "チ"
                "ﾂ" -> "ツ"
                "ﾃ" -> "テ"
                "ﾄ" -> "ト"
                "ﾅ" -> "ナ"
                "ﾆ" -> "ニ"
                "ﾇ" -> "ヌ"
                "ﾈ" -> "ネ"
                "ﾉ" -> "ノ"
                "ﾊ" -> "ハ"
                "ﾋ" -> "ヒ"
                "ﾌ" -> "フ"
                "ﾍ" -> "ヘ"
                "ﾎ" -> "ホ"
                "ﾏ" -> "マ"
                "ﾐ" -> "ミ"
                "ﾑ" -> "ム"
                "ﾒ" -> "メ"
                "ﾓ" -> "モ"
                "ﾔ" -> "ヤ"
                "ﾕ" -> "ユ"
                "ﾖ" -> "ヨ"
                "ﾗ" -> "ラ"
                "ﾘ" -> "リ"
                "ﾙ" -> "ル"
                "ﾚ" -> "レ"
                "ﾛ" -> "ロ"
                "ﾜ" -> "ワ"
                "ｦ" -> "ヲ"
                "ﾝ" -> "ン"
                "ｧ" -> "ァ"
                "ｨ" -> "ィ"
                "ｩ" -> "ゥ"
                "ｪ" -> "ェ"
                "ｫ" -> "ォ"
                "ｯ" -> "ッ"
                "ｬ" -> "ャ"
                "ｭ" -> "ュ"
                "ｮ" -> "ョ"
                "ｰ" -> "ー"
                "｡" -> "。"
                "､" -> "、"
                "｢" -> "「"
                "｣" -> "」"
                "･" -> "・"
                else -> "" // 半濁点・半濁音を削除
            }
        }
    }

    private fun String.toFullWidthAlphaNumAndSymbol(): String {
        return this.replace(Regex("[\\u0020-\\u007E]|\\u00A5")) {
            when (val target = it.value.codePointAt(0)) {
                in 0x0030..0x0039 -> {
                    // 数字
                    Char(target - 0x0030 + 0xFF10).toString()
                }
                in 0x0041..0x005A -> {
                    // アルファベット大文字
                    Char(target - 0x0041 + 0xFF21).toString()
                }
                in 0x0061..0x007A -> {
                    // アルファベット小文字
                    Char(target - 0x0061 + 0xFF41).toString()
                }
                else -> {
                    // 記号
                    when (it.value) {
                        " " -> "　"
                        "!" -> "！"
                        "\"" -> "”"
                        "#" -> "＃"
                        "$" -> "＄"
                        "%" -> "％"
                        "&" -> "＆"
                        "'" -> "’"
                        "(" -> "（"
                        ")" -> "）"
                        "*" -> "＊"
                        "+" -> "＋"
                        "," -> "，"
                        "-" -> "−"
                        "." -> "．"
                        "/" -> "／"
                        ":" -> "："
                        ";" -> "；"
                        "<" -> "＜"
                        "=" -> "＝"
                        ">" -> "＞"
                        "?" -> "？"
                        "@" -> "＠"
                        "[" -> "［"
                        "\\" -> "＼"
                        "]" -> "］"
                        "^" -> "＾"
                        "_" -> "＿"
                        "`" -> "｀"
                        "{" -> "｛"
                        "|" -> "｜"
                        "}" -> "｝"
                        "~" -> "〜"
                        "¥" -> "￥"
                        else -> ""
                    }
                }
            }
        }
    }

}

interface IStringConverter {
    fun toHalfWidth(value: String): String

    fun toFullWidth(value: String): String
}
