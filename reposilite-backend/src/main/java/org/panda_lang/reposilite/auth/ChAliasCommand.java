/*
 * Copyright (c) 2020 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.reposilite.auth;

import org.panda_lang.reposilite.console.ReposiliteCommand;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.util.List;

@Command(name = "chalias", description = "Change token alias")
final class ChAliasCommand implements ReposiliteCommand {

    @Parameters(index = "0", paramLabel = "<alias>", description = "alias to update")
    private String alias;
    @Parameters(index = "1", paramLabel = "<new alias>", description = "new token name")
    private String updatedAlias;

    private final TokenService tokenService;

    public ChAliasCommand(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public boolean execute(List<String> output) {
        return tokenService
                .updateToken(alias, token -> {
                    output.add("Token alias has been changed from '" + token.getAlias() + "' to '" + updatedAlias + "'");
                    token.setAlias(updatedAlias);
                })
                .onError(output::add)
                .isOk();
    }

}
