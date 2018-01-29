class project1 {
	public static void main( String argv[] ) throws java.io.IOException {
		Yylex yy = new Yylex( System.in );
		Yytoken t;
		while ( ( t = yy.yylex() ) != null )
			System.out.println( t );
		}
}
class Yytoken {
	Yytoken ( int id, String text ) {
		t_id = id;
		t_text = text;
	}
	private int t_id; // Token ID
	private String t_text; // Matching token string
	public String toString() {
		return "Token #" + t_id + ": " + t_text;
	}
}


class Yylex {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NOT_ACCEPT,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NOT_ACCEPT,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NOT_ACCEPT,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NOT_ACCEPT,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NOT_ACCEPT,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NOT_ACCEPT,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NOT_ACCEPT,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"47:8,53:2,54,47:2,52,47:18,53,32,46,47:2,28,33,47,38,39,26,24,36,25,37,27,4" +
"9,44:9,47,35,29,30,31,47:2,51:4,45,51,48:17,50,48:2,40,47,41,47:3,5,1,9,11," +
"4,15,21,23,16,48,8,3,17,6,2,18,48,7,10,14,12,22,19,13,20,48,42,34,43,47:2,0" +
":2")[0];

	private int yy_rmap[] = unpackFromString(1,160,
"0,1,2,1:3,3,1,4,5,6,7,8,1:9,9,10,11,12,1:6,13,14,11,15,16,11:3,1,11:14,17,1" +
"8,19,20,21,22,14,23,14,16,24,1,25,26,22,27,28,29,30,31,32,33,21,34,35,36,37" +
",38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62" +
",63,64,65,66,67,68,69,11,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86" +
",87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,11,102,103,104,105,106,107," +
"108,109,110,111,112,113")[0];

	private int yy_nxt[][] = unpackFromString(114,55,
"1,2,147:2,110,151,152,153,147,154,155,156,147:2,111,91,56,147,157,158,147:2" +
",112,147,3,4,5,6,7,8,9,10,11,12,57,13,14,15,16,17,18,19,20,21,22,147,63,66," +
"147,58,147:2,-1,23:2,-1:56,147,159,147:4,113,147:16,-1:20,114,147,-1:2,147," +
"114,147:2,-1:29,55,25,-1:57,26,-1:54,27,-1:54,28,-1:54,29,-1:57,30,-1:58,32" +
",-1:6,22,-1:4,22,-1:58,23:2,-1,147:23,-1:20,114,147,-1:2,147,114,147:2,-1:4" +
",25:51,-1,25,-1:5,71,-1:39,32,71,-1:3,32,-1:6,61:45,33,61:5,-1,61,-1:2,147:" +
"3,150,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4,36,-1:2,36:2,-1:3,36,-1," +
"36,-1:3,36,-1:28,36:2,-1:3,36,-1,36,-1:4,55:25,67,69,55:26,-1:2,147:5,65,14" +
"7:8,24,147,123,147:6,-1:20,114,147,-1:2,147,114,147:2,-1:37,31,-1:33,64,-1:" +
"23,32,-1:6,22,-1:4,22,64,-1:48,59,-1:4,59,-1:6,55:25,-1,75,55:26,-1:2,147:6" +
",34,147:16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:13,35,147:9,-1:20,114," +
"147,-1:2,147,114,147:2,-1:4,55:25,73,40,55:26,-1:2,147:3,37,147:19,-1:20,11" +
"4,147,-1:2,147,114,147:2,-1:4,147:3,38,147:19,-1:20,114,147,-1:2,147,114,14" +
"7:2,-1:27,77:2,-1:18,59,-1:4,59,-1:6,147:10,39,147:12,-1:20,114,147,-1:2,14" +
"7,114,147:2,-1:4,55:25,79,69,55:26,-1:2,147:7,41,147:15,-1:20,114,147,-1:2," +
"147,114,147:2,-1:4,55:25,67,75,55:26,-1:2,147:9,42,147:13,-1:20,114,147,-1:" +
"2,147,114,147:2,-1:4,147:3,43,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4," +
"55:25,79,60,55:26,-1:2,147:5,44,147:17,-1:20,114,147,-1:2,147,114,147:2,-1:" +
"4,147:5,45,147:17,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:20,46,147:2,-1:" +
"20,114,147,-1:2,147,114,147:2,-1:4,147:3,47,147:19,-1:20,114,147,-1:2,147,1" +
"14,147:2,-1:4,147:5,48,147:17,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:9,4" +
"9,147:13,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:5,50,147:17,-1:20,114,14" +
"7,-1:2,147,114,147:2,-1:4,147:17,51,147:5,-1:20,114,147,-1:2,147,114,147:2," +
"-1:4,147:19,52,147:3,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:3,53,147:19," +
"-1:20,114,147,-1:2,147,114,147:2,-1:4,147:9,54,147:13,-1:20,114,147,-1:2,14" +
"7,114,147:2,-1:4,147,62,147:2,122,147:18,-1:20,114,147,-1:2,147,114,147:2,-" +
"1:4,147:9,68,147:13,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:11,70,147:11," +
"-1:20,114,147,-1:2,147,114,147:2,-1:4,147:15,72,147:7,-1:20,114,147,-1:2,14" +
"7,114,147:2,-1:4,147:4,74,147:18,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:" +
"9,76,147:13,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:9,70,147:13,-1:20,114" +
",147,-1:2,147,114,147:2,-1:4,147:2,78,147:20,-1:20,114,147,-1:2,147,114,147" +
":2,-1:4,147:2,80,147:20,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:6,81,147:" +
"16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:5,82,147:17,-1:20,114,147,-1:2" +
",147,114,147:2,-1:4,147:2,83,147:20,-1:20,114,147,-1:2,147,114,147:2,-1:4,1" +
"47:4,84,147:18,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:10,85,147:12,-1:20" +
",114,147,-1:2,147,114,147:2,-1:4,147:2,86,147:20,-1:20,114,147,-1:2,147,114" +
",147:2,-1:4,147,87,147:21,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:4,88,14" +
"7:18,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:8,89,147:14,-1:20,114,147,-1" +
":2,147,114,147:2,-1:4,147:13,90,147:9,-1:20,114,147,-1:2,147,114,147:2,-1:4" +
",147:2,92,147:9,115,147:10,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:6,93,1" +
"47:16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147,94,147:21,-1:20,114,147,-1:" +
"2,147,114,147:2,-1:4,147:3,95,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4," +
"147:13,127,147:9,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:9,148,147:13,-1:" +
"20,114,147,-1:2,147,114,147:2,-1:4,147:18,128,147:4,-1:20,114,147,-1:2,147," +
"114,147:2,-1:4,147:4,129,147:8,130,147:9,-1:20,114,147,-1:2,147,114,147:2,-" +
"1:4,147:4,96,147:18,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:6,131,147:16," +
"-1:20,114,147,-1:2,147,114,147:2,-1:4,147:11,132,147:11,-1:20,114,147,-1:2," +
"147,114,147:2,-1:4,147:2,97,147:20,-1:20,114,147,-1:2,147,114,147:2,-1:4,14" +
"7:17,149,147:5,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:15,133,147:7,-1:20" +
",114,147,-1:2,147,114,147:2,-1:4,147:15,98,147:7,-1:20,114,147,-1:2,147,114" +
",147:2,-1:4,147:2,134,147:20,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:3,13" +
"5,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:4,137,147:18,-1:20,114,1" +
"47,-1:2,147,114,147:2,-1:4,147:10,99,147:12,-1:20,114,147,-1:2,147,114,147:" +
"2,-1:4,147:11,100,147:11,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:15,101,1" +
"47:7,-1:20,114,147,-1:2,147,114,147:2,-1:4,102,147:22,-1:20,114,147,-1:2,14" +
"7,114,147:2,-1:4,147:5,139,147:17,-1:20,114,147,-1:2,147,114,147:2,-1:4,147" +
":3,103,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:5,104,147:17,-1:20," +
"114,147,-1:2,147,114,147:2,-1:4,147:20,140,147:2,-1:20,114,147,-1:2,147,114" +
",147:2,-1:4,147:6,141,147:16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:3,14" +
"3,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:13,105,147:9,-1:20,114,1" +
"47,-1:2,147,114,147:2,-1:4,147:5,106,147:17,-1:20,114,147,-1:2,147,114,147:" +
"2,-1:4,147:6,107,147:16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:14,144,14" +
"7:8,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:16,145,147:6,-1:20,114,147,-1" +
":2,147,114,147:2,-1:4,147:4,108,147:18,-1:20,114,147,-1:2,147,114,147:2,-1:" +
"4,147:3,146,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:5,109,147:17,-" +
"1:20,114,147,-1:2,147,114,147:2,-1:4,147:15,136,147:7,-1:20,114,147,-1:2,14" +
"7,114,147:2,-1:4,147:2,138,147:20,-1:20,114,147,-1:2,147,114,147:2,-1:4,147" +
":6,142,147:16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:9,116,147:13,-1:20," +
"114,147,-1:2,147,114,147:2,-1:4,147:3,117,147:19,-1:20,114,147,-1:2,147,114" +
",147:2,-1:4,147:3,118,147:19,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:2,11" +
"9,147:20,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:13,120,147:9,-1:20,114,1" +
"47,-1:2,147,114,147:2,-1:4,147,121,147:21,-1:20,114,147,-1:2,147,114,147:2," +
"-1:4,147:6,124,147:16,-1:20,114,147,-1:2,147,114,147:2,-1:4,147:22,125,-1:2" +
"0,114,147,-1:2,147,114,147:2,-1:4,147,126,147:21,-1:20,114,147,-1:2,147,114" +
",147:2,-1:3");

	public Yytoken yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ return new Yytoken( 46, "id" ); }
					case -3:
						break;
					case 3:
						{ return new Yytoken( 18, "plus" ); }
					case -4:
						break;
					case 4:
						{ return new Yytoken( 19, "minus" ); }
					case -5:
						break;
					case 5:
						{ return new Yytoken( 20, "multiplication" ); }
					case -6:
						break;
					case 6:
						{ return new Yytoken( 21, "division" ); }
					case -7:
						break;
					case 7:
						{ return new Yytoken( 22, "mod" ); }
					case -8:
						break;
					case 8:
						{ return new Yytoken( 23, "less" ); }
					case -9:
						break;
					case 9:
						{ return new Yytoken( 200, "assignop" ); }
					case -10:
						break;
					case 10:
						{ return new Yytoken( 25, "greater" ); }
					case -11:
						break;
					case 11:
						{ return new Yytoken( 31, "not" ); }
					case -12:
						break;
					case 12:
						{
	System.out.println( "Token not implemented yet: " + yytext() );
}
					case -13:
						break;
					case 13:
						{ return new Yytoken( 33, "semicolon" ); }
					case -14:
						break;
					case 14:
						{ return new Yytoken( 34, "comma" ); }
					case -15:
						break;
					case 15:
						{ return new Yytoken( 35, "period" ); }
					case -16:
						break;
					case 16:
						{ return new Yytoken( 36, "leftparen" ); }
					case -17:
						break;
					case 17:
						{ return new Yytoken( 37, "rightparen" ); }
					case -18:
						break;
					case 18:
						{ return new Yytoken( 38, "leftbracket" ); }
					case -19:
						break;
					case 19:
						{ return new Yytoken( 39, "rightbracket" ); }
					case -20:
						break;
					case 20:
						{ return new Yytoken( 40, "leftbrace" ); }
					case -21:
						break;
					case 21:
						{ return new Yytoken( 41, "rightbrace" ); }
					case -22:
						break;
					case 22:
						{ return new Yytoken( 42, "intconstant" ); }
					case -23:
						break;
					case 23:
						{}
					case -24:
						break;
					case 24:
						{ return new Yytoken( 7, yytext() ); }
					case -25:
						break;
					case 25:
						{}
					case -26:
						break;
					case 26:
						{ return new Yytoken( 24, "lessequal" ); }
					case -27:
						break;
					case 27:
						{ return new Yytoken( 27, "equal" ); }
					case -28:
						break;
					case 28:
						{ return new Yytoken( 26, "greaterequal" ); }
					case -29:
						break;
					case 29:
						{ return new Yytoken( 28, "notequal" ); }
					case -30:
						break;
					case 30:
						{ return new Yytoken( 29, "and" ); }
					case -31:
						break;
					case 31:
						{ return new Yytoken( 30, "or" ); }
					case -32:
						break;
					case 32:
						{ return new Yytoken( 43, "doubleconstant" ); }
					case -33:
						break;
					case 33:
						{ return new Yytoken( 44, "stringconstant" ); }
					case -34:
						break;
					case 34:
						{ return new Yytoken( 6, yytext() ); }
					case -35:
						break;
					case 35:
						{ return new Yytoken( 9, yytext() ); }
					case -36:
						break;
					case 36:
						{ return new Yytoken(51, "intconstant" ); }
					case -37:
						break;
					case 37:
						{ return new Yytoken( 4, yytext() ); }
					case -38:
						break;
					case 38:
						{ return new Yytoken( 45, "booleanconstant" ); }
					case -39:
						break;
					case 39:
						{ return new Yytoken( 16, yytext() ); }
					case -40:
						break;
					case 40:
						{}
					case -41:
						break;
					case 41:
						{ return new Yytoken( 1, yytext() ); }
					case -42:
						break;
					case 42:
						{ return new Yytoken( 2, yytext() ); }
					case -43:
						break;
					case 43:
						{ return new Yytoken( 17, yytext() ); }
					case -44:
						break;
					case 44:
						{ return new Yytoken( 13, yytext() ); }
					case -45:
						break;
					case 45:
						{ return new Yytoken( 14, yytext() ); }
					case -46:
						break;
					case 46:
						{ return new Yytoken( 15, yytext() ); }
					case -47:
						break;
					case 47:
						{ return new Yytoken( 3, yytext() ); }
					case -48:
						break;
					case 48:
						{ return new Yytoken( 0, yytext() ); }
					case -49:
						break;
					case 49:
						{ return new Yytoken( 5, yytext() ); }
					case -50:
						break;
					case 50:
						{ return new Yytoken( 12, yytext() ); }
					case -51:
						break;
					case 51:
						{ return new Yytoken( 32, "assignop" ); }
					case -52:
						break;
					case 52:
						{ return new Yytoken( 11, yytext() ); }
					case -53:
						break;
					case 53:
						{ return new Yytoken( 10, yytext() ); }
					case -54:
						break;
					case 54:
						{ return new Yytoken( 8, yytext() ); }
					case -55:
						break;
					case 56:
						{ return new Yytoken( 46, "id" ); }
					case -56:
						break;
					case 57:
						{
	System.out.println( "Token not implemented yet: " + yytext() );
}
					case -57:
						break;
					case 58:
						{ return new Yytoken( 42, "intconstant" ); }
					case -58:
						break;
					case 59:
						{ return new Yytoken( 43, "doubleconstant" ); }
					case -59:
						break;
					case 60:
						{}
					case -60:
						break;
					case 62:
						{ return new Yytoken( 46, "id" ); }
					case -61:
						break;
					case 63:
						{
	System.out.println( "Token not implemented yet: " + yytext() );
}
					case -62:
						break;
					case 65:
						{ return new Yytoken( 46, "id" ); }
					case -63:
						break;
					case 66:
						{
	System.out.println( "Token not implemented yet: " + yytext() );
}
					case -64:
						break;
					case 68:
						{ return new Yytoken( 46, "id" ); }
					case -65:
						break;
					case 70:
						{ return new Yytoken( 46, "id" ); }
					case -66:
						break;
					case 72:
						{ return new Yytoken( 46, "id" ); }
					case -67:
						break;
					case 74:
						{ return new Yytoken( 46, "id" ); }
					case -68:
						break;
					case 76:
						{ return new Yytoken( 46, "id" ); }
					case -69:
						break;
					case 78:
						{ return new Yytoken( 46, "id" ); }
					case -70:
						break;
					case 80:
						{ return new Yytoken( 46, "id" ); }
					case -71:
						break;
					case 81:
						{ return new Yytoken( 46, "id" ); }
					case -72:
						break;
					case 82:
						{ return new Yytoken( 46, "id" ); }
					case -73:
						break;
					case 83:
						{ return new Yytoken( 46, "id" ); }
					case -74:
						break;
					case 84:
						{ return new Yytoken( 46, "id" ); }
					case -75:
						break;
					case 85:
						{ return new Yytoken( 46, "id" ); }
					case -76:
						break;
					case 86:
						{ return new Yytoken( 46, "id" ); }
					case -77:
						break;
					case 87:
						{ return new Yytoken( 46, "id" ); }
					case -78:
						break;
					case 88:
						{ return new Yytoken( 46, "id" ); }
					case -79:
						break;
					case 89:
						{ return new Yytoken( 46, "id" ); }
					case -80:
						break;
					case 90:
						{ return new Yytoken( 46, "id" ); }
					case -81:
						break;
					case 91:
						{ return new Yytoken( 46, "id" ); }
					case -82:
						break;
					case 92:
						{ return new Yytoken( 46, "id" ); }
					case -83:
						break;
					case 93:
						{ return new Yytoken( 46, "id" ); }
					case -84:
						break;
					case 94:
						{ return new Yytoken( 46, "id" ); }
					case -85:
						break;
					case 95:
						{ return new Yytoken( 46, "id" ); }
					case -86:
						break;
					case 96:
						{ return new Yytoken( 46, "id" ); }
					case -87:
						break;
					case 97:
						{ return new Yytoken( 46, "id" ); }
					case -88:
						break;
					case 98:
						{ return new Yytoken( 46, "id" ); }
					case -89:
						break;
					case 99:
						{ return new Yytoken( 46, "id" ); }
					case -90:
						break;
					case 100:
						{ return new Yytoken( 46, "id" ); }
					case -91:
						break;
					case 101:
						{ return new Yytoken( 46, "id" ); }
					case -92:
						break;
					case 102:
						{ return new Yytoken( 46, "id" ); }
					case -93:
						break;
					case 103:
						{ return new Yytoken( 46, "id" ); }
					case -94:
						break;
					case 104:
						{ return new Yytoken( 46, "id" ); }
					case -95:
						break;
					case 105:
						{ return new Yytoken( 46, "id" ); }
					case -96:
						break;
					case 106:
						{ return new Yytoken( 46, "id" ); }
					case -97:
						break;
					case 107:
						{ return new Yytoken( 46, "id" ); }
					case -98:
						break;
					case 108:
						{ return new Yytoken( 46, "id" ); }
					case -99:
						break;
					case 109:
						{ return new Yytoken( 46, "id" ); }
					case -100:
						break;
					case 110:
						{ return new Yytoken( 46, "id" ); }
					case -101:
						break;
					case 111:
						{ return new Yytoken( 46, "id" ); }
					case -102:
						break;
					case 112:
						{ return new Yytoken( 46, "id" ); }
					case -103:
						break;
					case 113:
						{ return new Yytoken( 46, "id" ); }
					case -104:
						break;
					case 114:
						{ return new Yytoken( 46, "id" ); }
					case -105:
						break;
					case 115:
						{ return new Yytoken( 46, "id" ); }
					case -106:
						break;
					case 116:
						{ return new Yytoken( 46, "id" ); }
					case -107:
						break;
					case 117:
						{ return new Yytoken( 46, "id" ); }
					case -108:
						break;
					case 118:
						{ return new Yytoken( 46, "id" ); }
					case -109:
						break;
					case 119:
						{ return new Yytoken( 46, "id" ); }
					case -110:
						break;
					case 120:
						{ return new Yytoken( 46, "id" ); }
					case -111:
						break;
					case 121:
						{ return new Yytoken( 46, "id" ); }
					case -112:
						break;
					case 122:
						{ return new Yytoken( 46, "id" ); }
					case -113:
						break;
					case 123:
						{ return new Yytoken( 46, "id" ); }
					case -114:
						break;
					case 124:
						{ return new Yytoken( 46, "id" ); }
					case -115:
						break;
					case 125:
						{ return new Yytoken( 46, "id" ); }
					case -116:
						break;
					case 126:
						{ return new Yytoken( 46, "id" ); }
					case -117:
						break;
					case 127:
						{ return new Yytoken( 46, "id" ); }
					case -118:
						break;
					case 128:
						{ return new Yytoken( 46, "id" ); }
					case -119:
						break;
					case 129:
						{ return new Yytoken( 46, "id" ); }
					case -120:
						break;
					case 130:
						{ return new Yytoken( 46, "id" ); }
					case -121:
						break;
					case 131:
						{ return new Yytoken( 46, "id" ); }
					case -122:
						break;
					case 132:
						{ return new Yytoken( 46, "id" ); }
					case -123:
						break;
					case 133:
						{ return new Yytoken( 46, "id" ); }
					case -124:
						break;
					case 134:
						{ return new Yytoken( 46, "id" ); }
					case -125:
						break;
					case 135:
						{ return new Yytoken( 46, "id" ); }
					case -126:
						break;
					case 136:
						{ return new Yytoken( 46, "id" ); }
					case -127:
						break;
					case 137:
						{ return new Yytoken( 46, "id" ); }
					case -128:
						break;
					case 138:
						{ return new Yytoken( 46, "id" ); }
					case -129:
						break;
					case 139:
						{ return new Yytoken( 46, "id" ); }
					case -130:
						break;
					case 140:
						{ return new Yytoken( 46, "id" ); }
					case -131:
						break;
					case 141:
						{ return new Yytoken( 46, "id" ); }
					case -132:
						break;
					case 142:
						{ return new Yytoken( 46, "id" ); }
					case -133:
						break;
					case 143:
						{ return new Yytoken( 46, "id" ); }
					case -134:
						break;
					case 144:
						{ return new Yytoken( 46, "id" ); }
					case -135:
						break;
					case 145:
						{ return new Yytoken( 46, "id" ); }
					case -136:
						break;
					case 146:
						{ return new Yytoken( 46, "id" ); }
					case -137:
						break;
					case 147:
						{ return new Yytoken( 46, "id" ); }
					case -138:
						break;
					case 148:
						{ return new Yytoken( 46, "id" ); }
					case -139:
						break;
					case 149:
						{ return new Yytoken( 46, "id" ); }
					case -140:
						break;
					case 150:
						{ return new Yytoken( 46, "id" ); }
					case -141:
						break;
					case 151:
						{ return new Yytoken( 46, "id" ); }
					case -142:
						break;
					case 152:
						{ return new Yytoken( 46, "id" ); }
					case -143:
						break;
					case 153:
						{ return new Yytoken( 46, "id" ); }
					case -144:
						break;
					case 154:
						{ return new Yytoken( 46, "id" ); }
					case -145:
						break;
					case 155:
						{ return new Yytoken( 46, "id" ); }
					case -146:
						break;
					case 156:
						{ return new Yytoken( 46, "id" ); }
					case -147:
						break;
					case 157:
						{ return new Yytoken( 46, "id" ); }
					case -148:
						break;
					case 158:
						{ return new Yytoken( 46, "id" ); }
					case -149:
						break;
					case 159:
						{ return new Yytoken( 46, "id" ); }
					case -150:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
