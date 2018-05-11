package com.example;

/**
 * パスワードパース用クラス
 * 
 * @since 1.0.0d 2018/05/08
 * @author atagawa
 *
 */
public class PasswordPolicyParser {
    static SystemValue sysVal = new SystemValue();
    private Policy policy;

    /**
     * パスワードポリシーパース用クラスのコンストラクタ
     */
    public PasswordPolicyParser() {
        this(sysVal.getDefaultLength(), sysVal.getDefaultLetterCase(), sysVal.getDefaultAcceptSymbolChar());
    }

    /**
     * パスワードポリシーパース用クラスのコンストラクタ
     * 
     * @param length:int
     * @param charCase:int
     * @param acceptSymbolChar:boolean
     */
    public PasswordPolicyParser(int length, int letterCase, boolean isAcceptSymbolChar) {
        this.policy = new Policy();
        policy.setPasswordLength(length);
        policy.setLetterCase(letterCase);
        policy.setAcceptSymbolChar(isAcceptSymbolChar);
    }

    // コマンドラインで取得した引数を分解し、エラーチェックおよび各変数にセットする
    public Policy parse(String... args) {
        Messages msg = new Messages();
        StringBuilder resMessages = new StringBuilder();

        for (int i = 0; i < args.length; ++i) {
            try {
                if ("-l".matches(args[i])) {
                    policy.setPasswordLength(Integer.parseInt(args[++i]));
                    if (policy.getPasswordLength() > sysVal.getMaxPasswordLength()) {
                        resMessages.append(
                                msg.getMessageWithFormat(MessageType.ERRMSG_OVER_PASSWORD_LENGTH_EXCEPTION, args[i]));
                    }
                } else if ("-c".matches(args[i])) {
                    policy.setLetterCase(Integer.parseInt(args[++i]));
                    if (policy.getLetterCase() >= sysVal.getOutOfLetterCaseSettings()) {
                        resMessages.append(
                                msg.getMessageWithFormat(MessageType.ERRMSG_OVER_RANGE_VALUE_EXCEPTION, args[i]));
                    }
                } else if ("-s".matches(args[i])) {
                    policy.setAcceptSymbolChar(Boolean.parseBoolean(args[++i]));
                } else {
                    resMessages.append(
                            msg.getMessageWithFormat(MessageType.ERRMSG_UNEXPECTED_ARGUMENTS_EXCEPTION, args[i]));
                }
            } catch (NumberFormatException e) {
                resMessages.append(msg.getMessageWithFormat(MessageType.ERRMSG_NUMBER_FORMAT_EXCEPTION, args[i]));
            } catch (ArrayIndexOutOfBoundsException e) {
                resMessages.append(
                        msg.getMessageWithFormat(MessageType.ERRMSG_ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION, args[--i]));
            }
        }
        if (resMessages.length() != 0) {
            throw new GeneratePasswordAppException(resMessages.toString());
        } else {
            return policy;
        }
    }
}
