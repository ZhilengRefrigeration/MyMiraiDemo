package com.function.main;

import com.function.jrrp.JrrpMain;
import net.mamoe.mirai.event.*;
import net.mamoe.mirai.event.events.MessageEvent;

/**
 * @author 制冷
 * @date 2023/5/21 14:59
 * @description Message
 * 消息监听类，专门用于消息监听
 */
public class MessageListening extends SimpleListenerHost {
    private JrrpMain jrrp = new JrrpMain();

    /**
     * 监听群聊或私聊消息
     * @param event 消息
     */
    @EventHandler
    private void GroupOrPrivateMessages(MessageEvent event){
        /**
         * 触发.help
         */
        if (event.getMessage().contentToString().equals(".help")) {
            event.getSubject().sendMessage("欢迎使用Dice");
        }

        /**
         * 触发.jrrp指令
         */
        if (event.getMessage().contentToString().equals(".jrrp")) {
            event.getSubject().sendMessage(event.getSenderName() + "今天的人品值是: " + jrrp.getUserCharacterValue((int) event.getSender().getId()));
        }
    }
}
