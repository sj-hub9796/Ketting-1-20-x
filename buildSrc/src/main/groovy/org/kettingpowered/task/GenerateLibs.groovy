package org.kettingpowered.task

import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.ResolvedArtifact
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

abstract class GenerateLibs extends DefaultTask {
    @OutputFile abstract RegularFileProperty getOutput()

    GenerateLibs(){
        outputs.upToDateWhen {false}
        outputs.cacheIf { false }
        output.convention(getProject().getLayout().getBuildDirectory().file("ketting_libraries.txt"))
    }

    @TaskAction
    void genActions() {
        def entries = new HashMap<GString, GString> ()
        getProject().configurations.installer.resolvedConfiguration.resolvedArtifacts.each { dep -> addEntry(dep, entries) }
        getProject().configurations.transitiveInstaller.resolvedConfiguration.resolvedArtifacts.each { dep -> addEntry(dep, entries) }

        output.get().asFile.text = entries.values().join('\n')
    }

    void addEntry(ResolvedArtifact dep, HashMap<GString, GString> entries) {
        def art = dep.moduleVersion.id
        if ('junit'.equals(art.name) && 'junit'.equals(art.group)) return;
        def mavenId = "$art.group:$art.name:$art.version" + (dep.classifier != null ? ":$dep.classifier" : "") + (dep.extension != null ? "@$dep.extension" : "")
        def key = "$art.group:$art.name:" + (dep.classifier != null ? ":$dep.classifier" : "") + (dep.extension != null ? "@$dep.extension" : "")

        if (entries.containsKey(key)) return;
        entries.put(key,"$dep.file.sha512\tSHA3-512\t$mavenId")
    }
}
