/*********************************************************************************
 *                                                                               *
 * The MIT License (MIT)                                                         *
 *                                                                               *
 * Copyright (c) 2022 xyit.com.cn and other contributors.                      *
 *                                                                               *
 * Permission is hereby granted, free of charge, to any person obtaining a copy  *
 * of this software and associated documentation files (the "Software"), to deal *
 * in the Software without restriction, including without limitation the rights  *
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell     *
 * copies of the Software, and to permit persons to whom the Software is         *
 * furnished to do so, subject to the following conditions:                      *
 *                                                                               *
 * The above copyright notice and this permission notice shall be included in    *
 * all copies or substantial portions of the Software.                           *
 *                                                                               *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR    *
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,      *
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE   *
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER        *
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, *
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN     *
 * THE SOFTWARE.                                                                 *
 *                                                                               *
 ********************************************************************************/
package io.spring.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Jason
 * @since Java 17+
 */
public class RepositoryPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {
        project.getRepositories().add(project.getRepositories().mavenLocal());

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("Snapshot");
            repo.setUrl(ProjectUtils.findProperty(project, "yunxiaoMavenSnapshotUrl", ""));
            repo.credentials(passwordCredentials -> {
                passwordCredentials.setUsername(ProjectUtils.findProperty(project, "yunxiaoMavenUsername", ""));
                passwordCredentials.setPassword(ProjectUtils.findProperty(project, "yunxiaoMavenPassword", ""));
            });
        }));

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("Release");
            repo.setUrl(ProjectUtils.findProperty(project, "yunxiaoMavenReleaseUrl", ""));
            repo.credentials(passwordCredentials -> {
                passwordCredentials.setUsername(ProjectUtils.findProperty(project, "yunxiaoMavenUsername", ""));
                passwordCredentials.setPassword(ProjectUtils.findProperty(project, "yunxiaoMavenPassword", ""));
            });
        }));

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("Aliyun");
            repo.setUrl("https://maven.aliyun.com/repository/public");
        }));

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("Tencent");
            repo.setUrl("https://mirrors.cloud.tencent.com/nexus/repository/maven-public");
        }));

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("SpringSnapshot");
            repo.setUrl("https://repo.spring.io/snapshot");
        }));

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("SpringMilestone");
            repo.setUrl("https://repo.spring.io/milestone");
        }));

        project.getRepositories().add(project.getRepositories().maven(repo -> {
            repo.setName("SpringRelease");
            repo.setUrl("https://repo.spring.io/release");
        }));

        project.getRepositories().add(project.getRepositories().mavenCentral());
    }
}
