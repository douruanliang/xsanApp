package top.douruanliang.xsan.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * author: dourl
 * created on: 2018/8/14 下午2:40
 * description:
 */
public enum EcIcons  implements Icon{
    icon_scan('\ue602'),
    icon_ali_pay('\ue606');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
