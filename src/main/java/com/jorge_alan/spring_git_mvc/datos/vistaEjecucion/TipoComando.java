package com.jorge_alan.spring_git_mvc.datos.vistaEjecucion;

public enum TipoComando {
    GIT("git") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    STASH_LIST("stash list") {
        @Override
        public String[] SplitArray() {
            return STASH_LIST.getSeparatorResult();
        }
    },
    STASH("stash") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    STASH_POP("stash pop") {
        @Override
        public String[] SplitArray() {
            return STASH_POP.getSeparatorResult();
        }
    },
    SWITCH("switch") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    LOG("log") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    PUSH("push origin \"%s\"") {
        @Override
        public String[] SplitArray() {
            return PUSH.getSeparatorResult();
        }
    },
    PULL("pull origin \"%s\"") {
        @Override
        public String[] SplitArray() {
            return PULL.getSeparatorResult();
        }
    },
    CLONE("clone \"%s\"") {
        @Override
        public String[] SplitArray() {
            return CLONE.getSeparatorResult();
        }
    },
    ADD("add") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    COMMIT("commit") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    INIT("init") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    BRANCH("branch") {

        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    REMOTE("remote") {
        @Override
        public String[] SplitArray() {
            return null;
        }
    },
    REMOTE_SET_URL("remote set-url") {
        @Override
        public String[] SplitArray() {
            return REMOTE_SET_URL.getSeparatorResult();
        }
    },
    GIT_REMOTE_ADD("remote add origin %s") {
        @Override
        public String[] SplitArray() {
            return GIT_REMOTE_ADD.SplitArray();
        }
    };

    public abstract String[] SplitArray();

    private String[] getSeparatorResult() {
        return this.valor.split("\\s+");
    }

    private String valor;

    public String getValor() {
        return valor;
    }

    private TipoComando(String valor) {
        this.valor = valor;
    }
}
