/**
 * Updated by niyatishah
 * Cite: http://introcs.cs.princeton.edu/java/95linear/Matrix.java.html
 */
public class Matrix {

        public  int row;
        public  int column;
        public  double[][] data;


        public Matrix(int row, int column) {
            this.row = row;
            this.column = column;
            data = new double[row][column];
        }


        public Matrix(double[][] data) {
            row = data.length;
            column = data[0].length;
            this.data = new double[row][column];
            for (int i = 0; i < row; i++)
                for (int j = 0; j < column; j++)
                    this.data[i][j] = data[i][j];
        }

        public double getvalue(int row,int column){
            return this.data[row][column];
        }


        public void setvalue(int row,int column,double data){
             this.data[row][column] = data;
        }

        // create and return the N-by-N identity matrix
        public static Matrix identity(int N) {
            Matrix I = new Matrix(N, N);
            for (int i = 0; i < N; i++)
                I.data[i][i] = 1;
            return I;
        }

        public int getRow(){
            return this.row;
        }

        public int getColumn(){
            return this.column;
        }


        // return C = A + B
        public Matrix plus(Matrix B) {
            Matrix A = this;
            if (B.row != A.row || B.column != A.column) throw new RuntimeException("Illegal matrix dimensions.");
            Matrix C = new Matrix(row, column);
            for (int i = 0; i < row; i++)
                for (int j = 0; j < column; j++)
                    C.data[i][j] = A.data[i][j] + B.data[i][j];
            return C;
        }


        // return C = A - B
        public Matrix minus(Matrix B) {
            Matrix A = this;
            if (B.row != A.row || B.column != A.column) throw new RuntimeException("Illegal matrix dimensions.");
            Matrix C = new Matrix(row, column);
            for (int i = 0; i < row; i++)
                for (int j = 0; j < column; j++)
                    C.data[i][j] = A.data[i][j] - B.data[i][j];
            return C;
        }



        // return C = A * B
        public Matrix times(Matrix B) {
            Matrix A = this;
            if (A.column != B.row) throw new RuntimeException("Illegal matrix dimensions.");
            Matrix C = new Matrix(A.row, B.column);
            for (int i = 0; i < C.row; i++)
                for (int j = 0; j < C.column; j++)
                    for (int k = 0; k < A.column; k++)
                        C.data[i][j] += (A.data[i][k] * B.data[k][j]);
            return C;
        }



        // print matrix to standard output
        public void show() {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++)
                    System.out.printf("%9.4f ", data[i][j]);
                System.out.println();
            }
        }


    }
