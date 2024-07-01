package org.kettingpowered.ketting.deobf;

import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.rewrite.RewritePolicy;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.Log4jLogEvent;

import java.util.function.Consumer;

@Plugin(name = "DeobfRewritePolicy", category = Core.CATEGORY_NAME, elementType = "rewritePolicy", printObject = true)
public class DeobfRewritePolicy implements RewritePolicy {

    private static Consumer<Throwable> deobfuscator;

    public static void setDeobfuscator(Consumer<Throwable> deobfuscator) {
        DeobfRewritePolicy.deobfuscator = deobfuscator;
    }

    public LogEvent rewrite(LogEvent source) {
        if (deobfuscator == null) return source;

        Throwable throwable = source.getThrown();
        if (throwable != null) {
            deobfuscator.accept(throwable);
            return new Log4jLogEvent.Builder(source).setThrownProxy(null).build();
        }
        return source;
    }

    @PluginFactory
    public static DeobfRewritePolicy createPolicy() {
        return new DeobfRewritePolicy();
    }
}
