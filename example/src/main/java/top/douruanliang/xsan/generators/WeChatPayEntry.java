package top.douruanliang.xsan.generators;


import top.douruanliang.xsan.annotations.PayEntryGenerator;
import top.douruanliang.xsan.core.wechat.template.WPayTemplate;

@PayEntryGenerator(
        packageName = "top.douruanliang.xsan",
        entryTemplate = WPayTemplate.class
)
public interface WeChatPayEntry {
}
