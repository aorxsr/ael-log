package org.slf4j.impl;

import java.io.PrintStream;

class OutputChoice {
    final OutputChoice.OutputChoiceType outputChoiceType;
    final PrintStream targetPrintStream;

    OutputChoice(OutputChoice.OutputChoiceType outputChoiceType) {
        this.outputChoiceType = outputChoiceType;
        if (outputChoiceType == OutputChoice.OutputChoiceType.CACHED_SYS_OUT) {
            this.targetPrintStream = System.out;
        } else if (outputChoiceType == OutputChoice.OutputChoiceType.CACHED_SYS_ERR) {
            this.targetPrintStream = System.err;
        } else {
            this.targetPrintStream = null;
        }

    }

    PrintStream getTargetPrintStream() {
        switch(this.outputChoiceType) {
        case SYS_OUT:
            return System.out;
        case SYS_ERR:
            return System.err;
        case CACHED_SYS_ERR:
        case CACHED_SYS_OUT:
        case FILE:
            return this.targetPrintStream;
        default:
            throw new IllegalArgumentException();
        }
    }

    static enum OutputChoiceType {
        SYS_OUT,
        CACHED_SYS_OUT,
        SYS_ERR,
        CACHED_SYS_ERR,
        FILE;

        private OutputChoiceType() {
        }
    }
}
