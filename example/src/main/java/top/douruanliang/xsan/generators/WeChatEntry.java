package top.douruanliang.xsan.generators;


import top.douruanliang.xsan.annotations.EntryGenerator;
import top.douruanliang.xsan.core.wechat.template.WXEntryTemplate;

@EntryGenerator(
        packageName = "top.douruanliang.xsan",
        entryTemplate = WXEntryTemplate.class
)
public interface WeChatEntry {
}
