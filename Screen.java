import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Screen extends JPanel implements MouseListener, ActionListener{
    private Graph<Location> graph;
    private Location[] cities;
    private BufferedImage image;
    private JTextField city1TF,city2TF;
    private JButton getDirectionsB;
    private Location city1, city2;
    private JTextArea directions;
    private DLList<Location> directionsList;
    private BufferedImage currentIMG;
    int distance;


    public Screen(){
        this.setLayout(null);
        this.setFocusable(true);
        addMouseListener(this);

        // making the cities
        graph = new Graph<Location>();
        cities = new Location[20];
        cities[0] = new Location("Madrid","MAD",425,350,"MAD.png");
        cities[1] = new Location("Barcelona","BCN",829,269,"BCN.png");
        cities[2] = new Location("Segovia","SGV",475,300,"SGV.png");
        cities[3] = new Location("Marbella","MRB",365,695,"MRB.png");
        cities[4] = new Location("Malaga","AGP",390,675,"AGP.png");
        cities[5] = new Location("Sevilla","SVQ",280,615,"SVQ.png");
        cities[6] = new Location("Bilbao","BIO",475,95,"BIO.png");
        cities[7] = new Location("Murcia","MUR",580,585,"MUR.png");
        cities[8] = new Location("Zaragoza","ZAR",625,260,"ZAR.png");
        cities[9] = new Location("Vigo","VGO",85,190,"VGO.png");
        cities[10] = new Location("Granada","GRA",450,650,"GRA.png");
        cities[11] = new Location("Valencia","VLC",660,470,"VLC.png");
        cities[12] = new Location("Cordoba","CDB",325,570,"CDB.png");
        cities[13] = new Location("Santander","SAN",430,80,"SAN.png");
        cities[14] = new Location("Pamplona","PAM",560,150,"PAM.png");
        cities[15] = new Location("Jaen","JAE",430,575,"JAE.png");
        cities[16] = new Location("Cadiz","CIZ",250,700,"CIZ.png");
        cities[17] = new Location("Burgos","BGS",430,150,"BGS.png");
        cities[18] = new Location("Salamanca","SLM",290,310,"SLM.png");
        cities[19] = new Location("Valladolid","VAL",350,260,"VAL.png");
        for (Location a : cities) {
            graph.add(a);
        }
        // Madrid 0
        graph.addEdge(cities[0],cities[2],58);
        graph.addEdge(cities[0],cities[18],133);
        graph.addEdge(cities[0],cities[11],221);
        graph.addEdge(cities[0],cities[15],210);

        // valencia 11
        graph.addEdge(cities[11],cities[7],135);

        // Jaen 15
        graph.addEdge(cities[15],cities[12],67);
        graph.addEdge(cities[15],cities[10],58);
        graph.addEdge(cities[15],cities[4],130);
        // murcia 7
        graph.addEdge(cities[7],cities[15],209);
        graph.addEdge(cities[7],cities[10],174);
        // malaga 4
        graph.addEdge(cities[4],cities[3],37);
        graph.addEdge(cities[4],cities[10],46);
        // cordoba 12
        graph.addEdge(cities[12],cities[5],87);
        // sevilla 5
        graph.addEdge(cities[5],cities[16],76);
        // salamanco 18
        graph.addEdge(cities[18],cities[19],75);
        // valladolid 19
        graph.addEdge(cities[19],cities[9],271);
        graph.addEdge(cities[19],cities[17],79);
        // burgos 17
        graph.addEdge(cities[17],cities[13],114);
        graph.addEdge(cities[17],cities[6],99);
        graph.addEdge(cities[17],cities[14],131);
        // Bilbao 
        graph.addEdge(cities[6],cities[13],62);
        graph.addEdge(cities[6],cities[14],96);
        // segovia 2
        graph.addEdge(cities[2],cities[8],255);
        graph.addEdge(cities[2],cities[19],71);
        // zaragoza 8
        graph.addEdge(cities[8],cities[14],114);
        graph.addEdge(cities[8],cities[1],193);



        // making background
        image = null;
        try {
            image = ImageIO.read(new File("SpainNoWords.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // user interface
        city1TF = new JTextField();
        city1TF.setFont(new Font("Arial", Font.PLAIN, 20));
        city1TF.setHorizontalAlignment(SwingConstants.LEFT);
        city1TF.setBounds(1010, 410, 80, 30);
        city1TF.setText("");
        this.add(city1TF);



        city2TF = new JTextField();
        city2TF.setFont(new Font("Arial", Font.PLAIN, 20));
        city2TF.setHorizontalAlignment(SwingConstants.LEFT);
        city2TF.setBounds(1110, 410, 80, 30);
        city2TF.setText("");
        this.add(city2TF);

        getDirectionsB = new JButton();
        getDirectionsB.setFont(new Font("Arial", Font.BOLD, 20));
        getDirectionsB.setHorizontalAlignment(SwingConstants.CENTER);
        getDirectionsB.setBounds(1010, 460, 180, 30);
        getDirectionsB.setText("get directions");
        getDirectionsB.addActionListener(this);
        this.add(getDirectionsB);
        

        directions = new JTextArea("");
        this.add(directions);
        directions.setBounds(1010,80, 230,250);  
        directions.setVisible(true);
        
        directionsList = new DLList<Location>();
        distance=0;
        currentIMG = null;

    }

    public Dimension getPreferredSize(){
		return new Dimension(1250,800);
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw all roads and cities
        g.drawImage(image,0, 0,1015,870, null);
        g.setColor(Color.BLACK);
        for (int i=0; i<graph.toList().size(); i++) {
            Location temp = (Location)(graph.toList().get(i));
            g.fillOval(temp.getX()-5,temp.getY()-5,10,10);
            g.drawString(temp.toString()+"("+temp.getABV()+")",temp.getX()+5,temp.getY()+5);
            for (int a=0; a<graph.toListConnections(graph.toList().get(i)).size(); a++) {
                Location temp2 = (Location)(graph.toListConnections(graph.toList().get(i)).get(a));
                g.drawLine(temp.getX(),temp.getY(), temp2.getX(),temp2.getY());
                g.drawString(""+graph.adjacentConnection(temp,temp2), (int)((temp.getX()+temp2.getX())/2),(int)((temp.getY()+temp2.getY())/2));
            }
        }
        // drawing user interface
        g.setColor(Color.GRAY);
        g.fillRect(1000,0,250,800);
        g.setColor(Color.WHITE);
        g.fillRect(1010,10,230,300);
        g.fillRect(1010,520,230,270);

        g.setColor(Color.BLACK);
        g.drawString("Image of City Pressed:", 1020,540);
        g.drawString("search for directions between", 1020,350);
        g.drawString("two cities (using abbrev):", 1020,375);


        // drawing the directions list of what the user inputted
        g.setColor(Color.RED);
        if (directionsList.size()>0) {
            g.drawString(directionsList.get(0)+" → "+directionsList.get(directionsList.size()-1), 1020,30);
            g.drawString("total distance: "+distance, 1020,60);
            
            for (int i=1; i<directionsList.size(); i++) {
                Location temp = directionsList.get(i-1);
                Location temp2 = directionsList.get(i);
                g.fillOval(temp.getX()-5,temp.getY()-5,10,10);
                g.fillOval(temp2.getX()-5,temp2.getY()-5,10,10);
                g.drawLine(temp.getX(),temp.getY(), temp2.getX(),temp2.getY());
                g.drawString(""+graph.adjacentConnection(temp,temp2), (int)((temp.getX()+temp2.getX())/2),(int)((temp.getY()+temp2.getY())/2));
            }
        }
        // drawing the image of the mouse clicked city
        if (currentIMG!=null) {
            g.drawImage(currentIMG,1020,560,200,220,null);
        }

    }
    
    public void actionPerformed(ActionEvent e) {
        directionsList.clear();
        // finds directions given 2 abbreviations of 2 cities
        if (e.getSource()==getDirectionsB) {
            Location start = graph.searchByName(city1TF.getText());
            Location finish = graph.searchByName(city2TF.getText());
            distance = graph.shortestPath(start,finish);
            directions.setText(graph.getDirections_toString(start,finish));
            directionsList = graph.getDirections(start, finish);

        }
        city1TF.setText("");
        city2TF.setText("");
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // prints current image for the last city pressed
        currentIMG = null;
        for (int i=0; i<graph.toList().size(); i++) {
            Location temp = (Location)(graph.toList().get(i));
            if (e.getX()>temp.getX()-10 && e.getX()<temp.getX()+30) {
                if (e.getY()>temp.getY()-10 && e.getY()<temp.getY()+30) {
                    //System.out.println(temp);
                    currentIMG = setImage(temp.getImage());
                }
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //System.out.println("Mouse pressed at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //System.out.println("Mouse released at: " + e.getX() + ", " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //System.out.println("Mouse entered component");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //System.out.println("Mouse exited component");
    }

    public BufferedImage setImage(String url) {
        BufferedImage image=null;
        try {                
          image = ImageIO.read(new File(url));
       } catch (IOException ex) {
            // handle exception...
            System.out.println(url+" FAILED");
       }
       return image;
    }
}