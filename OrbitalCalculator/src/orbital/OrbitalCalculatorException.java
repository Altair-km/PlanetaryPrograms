package orbital;
/**
     *Exception class to manage and represent exceptions within the OrbitalCalculator class.
     */
    public class OrbitalCalculatorException extends Exception {
    	/**
         *Orbital exception method, takes the error and outputs an error message.
         *
         *@param message Parses the message sent from the thrown error
         */
		public OrbitalCalculatorException(String message) {
            super(message);
        }

        //Override method
        public String toString() {
            return getMessage();
        }
    }
