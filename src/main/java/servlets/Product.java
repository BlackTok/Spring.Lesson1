package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class Product extends HttpServlet {
//    private int id, cost;
//    private String title;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] parameters = req.getParameterValues("param");

        HashMap<Integer, String[]> productList = addProduct(parameters);

        for (int i = 0; i < productList.size(); i++) {
            String[] titleAndCount = productList.get(i);
            resp.getWriter().println(String.format("%s - %s %s pieces.", i, titleAndCount[0], titleAndCount[1]));
        }
    }

    public HashMap<Integer, String[]> addProduct(String[] parameters) {
        HashMap<Integer, String[]> productList = new HashMap<>();
        int countParam = 0;
        String[] product = new String[2];
        Boolean skip, ext;

        for (int i = 0; (i < 10) && (i < (int)(parameters.length / 2)); i++) {
            skip = false;
            ext = false;
            for (int j = 0; j < 2; j++) {
                if (countParam >= parameters.length) {
                    ext = true;
                    break;
                }

                if (j == 1) {
                    try{
                        Integer.parseInt(parameters[countParam]);
                    }
                    catch (NumberFormatException ex){
                        skip = true;
                    }
                }

                product[j] = parameters[countParam];
                countParam++;
            }

            if (skip) {
                i--;
                continue;
            }

            if (ext)
                break;

            productList.put(i, product.clone());
        }

        return productList;
    }
}
