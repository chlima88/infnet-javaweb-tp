package br.edu.infnet.tpapp.util;

public enum Constants {;


    public enum CustomerRank {;

        public enum Level3 {;
            public static final String NAME = "Elite";
            public static final float DISCOUNT_TX = (float) 0.15;
        }

        public enum Level2 {;
            public static final String NAME = "Premium";
            public static final float DISCOUNT_TX = (float) 0.1;
        }

        public enum Level1 {;
            public static final String NAME = "Basic";
            public static final float DISCOUNT_TX = (float) 0;
        }
    }
}
