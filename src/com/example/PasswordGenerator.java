package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * パスワード生成クラス
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 */
public class PasswordGenerator {

    /**
     * パスワード生成クラスのコンストラクタ
     */
    public PasswordGenerator() {
    }

    /**
     * ポリシーに沿ってパスワードを生成するメソッド
     * 
     * @param parser:PasswordPolicyParser
     * @return result:String パスワードを表す文字列
     */
    public String generatePassword(Policy policy, Random randomNumberGenerator) {
        Messages messages = new Messages();
        Map<Character, CharacterType> symbolCharTypes = new HashMap<>();
        symbolCharTypes.put('1', CharacterType.ALL_SPECIAL_CHAR_IS_OK);
        symbolCharTypes.put('2', CharacterType.SPECIFIED_CHAR_IS_OK);
        symbolCharTypes.put('3', CharacterType.SPECIFIED_CHAR_IS_NG);

        final int IDX_HOW_TO_HANDLE = 0;
        final int IDX_START_ACTUAL = 1;

        char[] prohibitionChars = {};
        if (policy.isAcceptSymbolChar()) {
            Utility.outPutConsoleMessage(messages.getMessage(MessageType.INFMSG_DEFAULT_SPETIAL_CHARS));
            // Utility.outPutConsoleMessage(messages.getMessage("default.acceptSpecialChars"));
            Utility.outPutConsoleMessage(messages.getMessage(MessageType.INFMSG_IGNORE_SPETIAL_CHAR_OPERATION));

            try (InputStreamReader s = new InputStreamReader(System.in); BufferedReader br = new BufferedReader(s)) {
                String retString = br.readLine();

                if (!symbolCharTypes.containsKey(Integer.parseInt(String.valueOf(retString.charAt(0))))) {

                }

                // TODO 先頭の1,2,3の確認
                //

                Pattern pattern = Pattern.compile(""/* BANNED_INPUT_CHARS */);
                Matcher matcher = pattern.matcher(retString);
                if (matcher.find()) {
                    Utility.outputErrorMessage(
                            messages.getMessageWithFormat(MessageType.ERRMSG_INPUT_NON_SPECIAL_CHAR, matcher.group()));
                    return "";
                } else {
                    CharacterType charaType = symbolCharTypes.get(retString.charAt(IDX_HOW_TO_HANDLE));
                    switch (charaType) {
                    case ALL_SPECIAL_CHAR_IS_OK: /* 特殊文字を全て使用可能 */
                        // 処理不要
                        break;
                    case SPECIFIED_CHAR_IS_OK: /* 指定した文字のみ使用可能 */
                        prohibitionChars = retString.substring(IDX_START_ACTUAL).toCharArray();
                        break;
                    case SPECIFIED_CHAR_IS_NG: /* 指定した文字を使用不可能にする */
                        prohibitionChars = retString.substring(IDX_START_ACTUAL).toCharArray();
                        break;
                    default:
                        Utility.outputErrorMessage(messages.getMessageWithFormat(
                                MessageType.ERRMSG_INPUT_INCOMPATIBLE_VALUE, retString.charAt(IDX_HOW_TO_HANDLE)));
                        return "";

                    }
                }

            } catch (IOException e) {
                // TODO Comments
                throw new GeneratePasswordAppException();
            }
        }
        // TODO 仕様文字列生成
        Set<Character> set = new HashSet<Character>();
        // ASCIIでの!から~までの連続した文字を取得している
        for (int i = '!'; i < '~'; i++) {
            set.add((char) i);
        }
        if (prohibitionChars.length != 0) {
            for (char c : prohibitionChars) {
                set.remove(c);
            }
        }
        Character[] charList = new Character[set.size()];
        set.toArray(charList);
        StringBuilder result = new StringBuilder();
        while (result.length() < policy.getPasswordLength()) {
            int a = Math.abs(randomNumberGenerator.nextInt()) % charList.length;
            result.append(charList[a]);
        }
        return result.toString();
    }

}
