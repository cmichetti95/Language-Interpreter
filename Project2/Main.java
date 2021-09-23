class Main {

	public static void main(String[] args) {

		//Initialize scanner with the input file
		Scanner S = new Scanner(args[0]);
		Parser.scanner = S;

		Prog prog = new Prog();

		prog.parse();

		prog.semantic();

		prog.print();
	}
}