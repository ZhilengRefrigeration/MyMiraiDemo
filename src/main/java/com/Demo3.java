package com;

import com.function.main.MessageListening;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;


public final class Demo3 extends JavaPlugin {
    public static final Demo3 INSTANCE = new Demo3();

    private Demo3() {
        super(new JvmPluginDescriptionBuilder("com.example.demo3", "0.1.0")
                .name("Dice-Mirai-ResetVersion")
                .info("Dice重制版，解决为了解决Mirai框架登录问题更新Mirai导致溯洄系Dice无法使用的临时替代版，后续请等待溯洄系Dice更新！")
                .author("制冷")
                .build());
    }

    @Override
    public void onEnable() {
        EventChannel<Event> channel = GlobalEventChannel.INSTANCE;

        //注册
        channel.registerListenerHost(new MessageListening());


    }
}