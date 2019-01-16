package top.douruanliang.xsan.generators;


import top.douruanliang.xsan.annotations.AppRegisterGenerator;
import top.douruanliang.xsan.annotations.EntryGenerator;
import top.douruanliang.xsan.core.wechat.template.AppRegisterTemplate;

@AppRegisterGenerator(
        packageName = "top.douruanliang.xsan",
        entryTemplate = AppRegisterTemplate.class
)
public interface AppRegisterEntry {
}
