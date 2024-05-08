package dev.sashimono.builder.compiler;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

import dev.sashimono.builder.dependencies.ResolvedDependency;
import dev.sashimono.builder.tool.AbstractJavaToolTask;
import dev.sashimono.builder.util.TaskMap;

public class JavaCompilerTask extends AbstractJavaToolTask implements Function<TaskMap, CompileResult> {

    public JavaCompilerTask(final List<Path> sourceDirectories) {
        super(sourceDirectories);
    }

    @Override
    public CompileResult apply(final TaskMap taskMap) {
        //grab both the downloaded and compiled dependencies
        final List<Path> deps = taskMap.results(ResolvedDependency.class).stream().map(ResolvedDependency::path).toList();
        final JavaCompiler compiler = JavaCompiler.build(deps, sourceDirectories);

        return new CompileResult(compiler.process());
    }
}
