package com.jorge_alan.spring_git_mvc.datos.vistaEjecucion;

public enum WildCard {

    ADD_ALL("* --all") {
        @Override
        public String[] CardSeparator() {
            return ADD_ALL.Extensiones();
        }
    },
    COMMIT_BODY_TITLE("-m \"%s\" -m \"%s\"") {
        @Override
        public String[] CardSeparator() {
            return COMMIT_BODY_TITLE.Extensiones();
        }
    },
    GIT_COMMIT_TITLE("-m \"%s\"") {
        @Override
        public String[] CardSeparator() {
            return GIT_COMMIT_TITLE.Extensiones();
        }
    },
    SQUASH_MERGE("--squash") {
        @Override
        public String[] CardSeparator() {
            return new String[]{SQUASH_MERGE.wildCard};
        }
    },
    MERGE_CHECK_NO_COMMIT("--no-commit") {
        @Override
        public String[] CardSeparator() {
            return new String[]{MERGE_CHECK_NO_COMMIT.wildCard};
        }

    },
    GIT_STATUS_FILES("-s") {
        @Override
        public String[] CardSeparator() {
            return new String[]{GIT_STATUS_FILES.wildCard};
        }
    },
    FORMAT_LOG_BRANCHES("--graph --oneline --all") {
        @Override
        public String[] CardSeparator() {
            return FORMAT_LOG_BRANCHES.Extensiones();
        }
    },
    PUSH_PULL("--force") {
        @Override
        public String[] CardSeparator() {
            return new String[]{PUSH_PULL.wildCard};
        }
    },
    CHANGE_AND_CREATE_SWITCH("-c") {
        @Override
        public String[] CardSeparator() {
            return new String[]{CHANGE_AND_CREATE_SWITCH.wildCard};
        }

    },
    BRANCH_ONLY_REMOTES("-r") {
        @Override
        public String[] CardSeparator() {
            return new String[]{BRANCH_ONLY_REMOTES.wildCard};
        }
    },
    VERBOSE("-v") {
        @Override
        public String[] CardSeparator() {
            return new String[]{VERBOSE.wildCard};
        }
    };

    private String wildCard;

    public abstract String[] CardSeparator();

    private String[] Extensiones() {
        return this.wildCard.split("\\s+");
    }

    public String getWildCard() {
        return wildCard;
    }

    private WildCard(String wildCard) {
        this.wildCard = wildCard;
    }

}
