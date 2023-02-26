package org.playbox.instances.limbo.consts;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class LoginMessages {
    public static final Component REPEATABLE_MESSAGE = MiniMessage.miniMessage().deserialize(
            "<blue><bold>Авторизация<newline><reset>" +
                    " <gray>Данный аккаунт зарегистрирован на сервере, так что вам нужно авторизоваться, воспользовавшись командой:<newline>" +
                    "<newline>" +
                    " <blue>/<white>login <blue>[<white>пароль<blue>]<newline>" +
                    "<newline>" +
                    " <gray>Если вы <underlined>забыли пароль<reset><gray>, то вы можете обратиться за помощью на наш дискорд-сервер: <dark_gray>playbox.network/discord<newline>"
    );

    public static final Component PROCESSING = MiniMessage.miniMessage().deserialize(
            "<blue><bold>Авторизация<newline><reset>" +
                    "<newline>" +
                    " <gray>Авторизовываемся...<newline><reset>" +
                    " <gray>Это должно занять где-то <underlined>1-5 секунд<reset><gray>...<newline><reset>" +
                    "<newline>"
    );

    public static final Component NOT_REGISTERED = MiniMessage.miniMessage().deserialize(
            "<blue><bold>Авторизация<newline><reset>" +
                    "<newline>" +
                    " <gray>Данный аккаунт <red>не зарегистрирован на сервере<gray>.<newline><reset>" +
                    " <gray>Используйте команду <blue>/<white>register <gray>для регистрации." +
                    "<newline>"
    );

    public static final Component ERROR = MiniMessage.miniMessage().deserialize(
            "<blue><bold>Авторизация<newline><reset>" +
                    "<newline>" +
                    " <gray>Во время авторизации <red>произошла ошибка<gray>. Вероятнее всего, вы ввели неправильный пароль. Пожалуйста, попробуйте ещё раз.<newline><reset>" +
                    "<newline>"
    );

    public static final Component LOGGED_IN = MiniMessage.miniMessage().deserialize(
            "<bold><gradient:#83a4d4:#b6fbff>playbox<newline><reset>" +
                    "<newline>" +
                    " <gray>Вы успешно авторизовались. Добро пожаловать на сервер!<newline>" +
                    " <blue>Хаски: <gray>Я хз что сюда вписывать<newline>" +
                    "<newline>"
    );
}
