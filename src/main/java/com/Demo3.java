package com;

import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.Event;
import net.mamoe.mirai.event.EventChannel;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.FriendMessageEvent;

public final class Demo3 extends JavaPlugin {
    public static final Demo3 INSTANCE = new Demo3();

    private Demo3() {
        super(new JvmPluginDescriptionBuilder("com.example.demo3", "0.1.0")
                .name("Demo3")
                .info("随便写着玩")
                .author("制冷")
                .build());
    }

    @Override
    public void onEnable() {
        EventChannel<Event> channel = GlobalEventChannel.INSTANCE.context(this.getCoroutineContext());
        channel.subscribeAlways(FriendMessageEvent.class, event -> {
            if (event.getMessage().contentToString().contains("hello")) {
                event.getSender().sendMessage("Hello!" + event.getSenderName());
                System.out.println("处理了一次请求");
            }
        });
    }
}