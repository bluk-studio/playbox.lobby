package org.playbox.instances.limbo.consts;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class RegisterMessages {
    public static final Component REPEATABLE_MESSAGE = MiniMessage.miniMessage().deserialize(
            "<blue><bold>Регистрация<newline><reset>" +
                    " <gray>Данный аккаунт ещё не зарегистрирован на сервере. Для того, что бы зарегистрироваться, вы можете воспользоваться командой:<newline>" +
                    "<newline>" +
                    " <blue>/<white>register <blue>[<white>пароль<blue>] <blue>[<white>повтор пароля<blue>]<newline>" +
                    "<newline>" +
                    " <gray>Если у вас возникли &nкакие-либо проблемы &7во время регистрации, то вы всегда можете обратиться к нам в дискорд по ссылке: <dark_gray>playbox.network/discord<newline>"
    );
}
