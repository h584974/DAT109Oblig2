package utils;

public enum Utleiegruppe {
	
	A {
		public String toString() {
			return "Liten Bil";
		}
	},
	
	B {
		public String toString() {
			return "Mellomstor Bil";
		}
	},
	
	C {
		public String toString() {
			return "Stor Bil";
		}
	},
	
	D {
		public String toString() {
			return "Stasjonsvogn";
		}
	}

}
