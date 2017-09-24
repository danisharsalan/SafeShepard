package com.example.android.crimedirections;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Coordination;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.Random;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, DirectionCallback {

    private MapView mMapView;
    private GoogleMap googleMap;

    private static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private LatLng union = new LatLng(40.109284,-88.2272228);
    private LatLng enterpriseWorks = new LatLng(40.091302, -88.240181);

    private Direction leftRoute1;
    private Direction rightRoute1;
    private Direction leftRoute2;
    private Direction rightRoute2;
    private Direction leftRoute3;
    private Direction rightRoute3;
    private Direction leftRoute4;
    private Direction rightRoute4;
    private Direction leftRoute5;
    private Direction rightRoute5;
    private Direction leftRoute6;
    private Direction rightRoute6;
    private Direction leftRoute7;
    private Direction rightRoute7;

    RequestQueue queue;
    private ArrayList<JSONObject> returnedMeanValues;

    private ArrayList<ArrayList<LatLng>> collectionOfLeftRoutes = new ArrayList<>();
    private ArrayList<ArrayList<LatLng>> collectionOfRightRoutes = new ArrayList<>();

    double[][] crimeData = {{40.128817,-88.219523},{40.11149,-88.216065},{40.103298,-88.188634},{40.103298,-88.188634},{40.093809,-88.200683},{40.107733,-88.197909},{40.093854,-88.191858},{40.123835,-88.199619},{40.08534,-88.1905},{40.119145,-88.216943},{40.120073,-88.221997},{40.098342,-88.204897},{40.093854,-88.191858},{40.117293,-88.207812},{40.113439,-88.207399},{40.122074,-88.197039},{40.098342,-88.204897},{40.093809,-88.200683},{40.085799,-88.190537},{40.128807,-88.220202},{40.105719,-88.190787},{40.085799,-88.190537},{40.112459,-88.208926},{40.106497,-88.217686},{40.112459,-88.208926},{40.128807,-88.220202},{40.094723,-88.194808},{40.105719,-88.190787},{40.094723,-88.194808},{40.112833,-88.219436},{40.126964,-88.199653},{40.102407,-88.198387},{40.119145,-88.216943},{40.122074,-88.197039},{40.107733,-88.197909},{40.083547,-88.220234},{40.093854,-88.191858},{40.117293,-88.207812},{40.123835,-88.199619},{40.123701,-88.219465},{40.116511,-88.210833},{40.102263,-88.18881},{40.107657,-88.204908},{40.1135726,-88.2114416},{40.102263,-88.18881},{40.1267,-88.207716},{40.116472,-88.214777},{40.085799,-88.190537},{40.122074,-88.197039},{40.112553,-88.203804},{40.117293,-88.207812},{40.122818,-88.218169},{40.122818,-88.218169},{40.099337,-88.183286},{40.115412,-88.207514},{40.098287,-88.187614},{40.122818,-88.218169},{40.1174,-88.22713},{40.113563,-88.205003},{40.128817,-88.219523},{40.1288359,-88.2218475},{40.110753,-88.208973},{40.112482,-88.206884},{40.110559,-88.192206},{40.112458,-88.207457},{40.116522,-88.209865},{40.100478,-88.18557},{40.1174,-88.22713},{40.123835,-88.199619},{40.1174,-88.22713},{40.1174,-88.22713},{40.117433,-88.210836},{40.122074,-88.197039},{40.138776,-88.182329},{40.122071,-88.198392},{40.122071,-88.198392},{40.112553,-88.203804},{40.117293,-88.207812},{40.140743,-88.183893},{40.122071,-88.198392},{40.111654,-88.19223},{40.098346,-88.190299},{40.082556,-88.185259},{40.103603,-88.190302},{40.138776,-88.182329},{40.123835,-88.199619},{40.105115,-88.176572},{40.116517,-88.210328},{40.083361,-88.190494},{40.103603,-88.190302},{40.122071,-88.198392},{40.122071,-88.198392},{40.082556,-88.185259},{40.112553,-88.203804},{40.098346,-88.190299},{40.117293,-88.207812},{40.111654,-88.19223},{40.1237036,-88.2284087},{40.112267,-88.216061},{40.122074,-88.197039},{40.112459,-88.208926},{40.115054,-88.182525},{40.123835,-88.199619},{40.104866,-88.176392},{40.123835,-88.199619},{40.110753,-88.208973},{40.112267,-88.216061},{40.113439,-88.207399},{40.128807,-88.220202},{40.112459,-88.208926},{40.126964,-88.199653},{40.102263,-88.18881},{40.095949,-88.190685},{40.116472,-88.214777},{40.102263,-88.18881},{40.107657,-88.204908},{40.1267,-88.207716},{40.116511,-88.210833},{40.094723,-88.194808},{40.094723,-88.194808},{40.085448,-88.196543},{40.094723,-88.194808},{40.085448,-88.196543},{40.105115,-88.176572},{40.116511,-88.210833},{40.094723,-88.194808},{40.122074,-88.197039},{40.101024,-88.184646},{40.101024,-88.184646},{40.101866,-88.201818},{40.110888,-88.197804},{40.117293,-88.207812},{40.1243118,-88.1992498},{40.123777,-88.205344},{40.123835,-88.199619},{40.114371,-88.208958},{40.114371,-88.208958},{40.090692,-88.191725},{40.123836,-88.199993},{40.112553,-88.203804},{40.093854,-88.191858},{40.115412,-88.207514},{40.1243118,-88.1992498},{40.122402,-88.219437},{40.123701,-88.219465},{40.099977,-88.176057},{40.123701,-88.219465},{40.119653,-88.206134},{40.123835,-88.199619},{40.103054,-88.205007},{40.126964,-88.199653},{40.093854,-88.191858},{40.103054,-88.205007},{40.117424,-88.213451},{40.100842,-88.195591},{40.1033045,-88.1875146},{40.1033045,-88.1875146},{40.083836,-88.181524},{40.117424,-88.213451},{40.106497,-88.217686},{40.117433,-88.210836},{40.122818,-88.218169},{40.119653,-88.206134},{40.125562,-88.200339},{40.113563,-88.205003},{40.093809,-88.200683},{40.093809,-88.200683},{40.119177,-88.221754},{40.112473,-88.209659},{40.122074,-88.197039},{40.118336,-88.215732},{40.075038,-88.184459},{40.122818,-88.218169},{40.123835,-88.199619},{40.11641,-88.224666},{40.123701,-88.219465},{40.110631,-88.217768},{40.098346,-88.190299},{40.127653,-88.219526},{40.104017,-88.177639},{40.1245989,-88.2452199},{40.0937854,-88.2449444},{40.1118244,-88.2432349},{40.1182567,-88.2469537},{40.1127899,-88.272408},{40.1274435,-88.276964},{40.105755,-88.2432581},{40.1295129,-88.23884},{40.11548,-88.2724498},{40.1145875,-88.2724358},{40.0999018,-88.293322},{40.0940869,-88.253495},{40.0927019,-88.2783062},{40.096795,-88.253489},{40.107166,-88.2761581},{40.1146405,-88.284953},{40.1185278,-88.2559566},{40.1228887,-88.2603028},{40.1230489,-88.2485215},{40.1165264,-88.2425699},{40.137263,-88.250799},{40.1114663,-88.2335649},{40.1447314,-88.2463723},{40.1089791,-88.2743087},{40.1295889,-88.25488},{40.109464,-88.2849686},{40.1200536,-88.2424758},{40.1101781,-88.2434586},{40.1423319,-88.2429304},{40.1421141,-88.2506254},{40.141395,-88.2579006},{40.1164442,-88.2767518},{40.1272359,-88.262709},{40.1400992,-88.2497564},{40.0854921,-88.2834071},{40.1254738,-88.2501964},{40.1143258,-88.2820817},{40.1129358,-88.2876783}};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // *** IMPORTANT ***
        // MapView requires that the Bundle you pass contain _ONLY_ MapView SDK
        // objects or sub-Bundles.
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(mapViewBundle);

        mMapView.getMapAsync(this);



        Intent i = getIntent();
        String lat1Input = i.getStringExtra("lat1");
        String long1Input = i.getStringExtra("long1");
        String lat2Input = i.getStringExtra("lat2");
        String long2Input = i.getStringExtra("long2");

        Log.d("lat1", lat1Input);
        Log.d("long1", long1Input);
        Log.d("lat2", lat2Input);
        Log.d("long2", long2Input);

        union = new LatLng(Double.parseDouble(lat1Input), Double.parseDouble(long1Input));
        enterpriseWorks = new LatLng(Double.parseDouble(lat2Input), Double.parseDouble(long2Input));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAPVIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAPVIEW_BUNDLE_KEY, mapViewBundle);
        }

        mMapView.onSaveInstanceState(mapViewBundle);
    }

    public void requestDirection(LatLng fromPoint, LatLng toPoint) {
        GoogleDirection.withServerKey("AIzaSyAvd1Ley2F6_3lgmOaPrJeSbEAgnHwjU2I")
                .from(fromPoint)
                .to(toPoint)
                .transportMode(TransportMode.WALKING)
                .alternativeRoute(true)
                .execute(this);
    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            if(leftRoute1 == null) {
                leftRoute1 = direction;
                Log.d("lef1", "declared");
            } else if(rightRoute1 == null) {
                rightRoute1 = direction;
                Log.d("rig1", "declared");
            } else if(leftRoute2 == null) {
                leftRoute2 = direction;
                Log.d("lef2", "declared");
            } else if(rightRoute2 == null) {
                rightRoute2 = direction;
                Log.d("rig2", "declared");
            } else if(leftRoute3 == null) {
                leftRoute3 = direction;
                Log.d("lef3", "declared");
            } else if(rightRoute3 == null) {
                rightRoute3 = direction;
                Log.d("rig3", "declared");
            } else if(leftRoute4 == null) {
                leftRoute4 = direction;
                Log.d("lef4", "declared");
            } else if(rightRoute4 == null) {
                rightRoute4 = direction;
                Log.d("rig4", "declared");
            } else if(leftRoute5 == null) {
                leftRoute5 = direction;
                Log.d("lef5", "declared");
            } else if(rightRoute5 == null) {
                rightRoute5 = direction;
                Log.d("rig5", "declared");
            } else if(leftRoute6 == null) {
                leftRoute6 = direction;
                Log.d("lef6", "declared");
            } else if(rightRoute6 == null) {
                rightRoute6 = direction;
                Log.d("rig6", "declared");
            } else if(leftRoute7 == null) {
                leftRoute7 = direction;
                Log.d("lef7", "declared");
            } else if(rightRoute7 == null) {
                rightRoute7 = direction;
                Log.d("rig7", "declared");
                drawFullRoutes();
            }
        }
    }

    public void drawFullRoutes() {
        //Log.d("summ " + i, direction.getRouteList().get(i).getSummary());
        Random rand1 = new Random();
        int directionThru1 = Color.rgb(rand1.nextInt(), rand1.nextInt(), rand1.nextInt());
        ArrayList<LatLng> directionPositionListLeft1 = leftRoute1.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight1 = rightRoute1.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft1, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight1, 5, Color.RED));


        Random rand2 = new Random();
        int directionThru2 = Color.rgb(rand2.nextInt(), rand2.nextInt(), rand2.nextInt());
        ArrayList<LatLng> directionPositionListLeft2 = leftRoute2.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight2 = rightRoute2.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft2, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight2, 5, Color.RED));


        Random rand3 = new Random();
        int directionThru3 = Color.rgb(rand3.nextInt(), rand3.nextInt(), rand3.nextInt());
        ArrayList<LatLng> directionPositionListLeft3 = leftRoute3.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight3 = rightRoute3.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft3, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight3, 5, Color.RED));


        Random rand4 = new Random();
        int directionThru4 = Color.rgb(rand4.nextInt(), rand4.nextInt(), rand4.nextInt());
        ArrayList<LatLng> directionPositionListLeft4 = leftRoute4.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight4 = rightRoute4.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft4, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight4, 5, Color.RED));


        Random rand5 = new Random();
        int directionThru5 = Color.rgb(rand5.nextInt(), rand5.nextInt(), rand5.nextInt());
        ArrayList<LatLng> directionPositionListLeft5 = leftRoute5.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight5 = rightRoute5.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft5, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight5, 5, Color.RED));


        Random rand6 = new Random();
        int directionThru6 = Color.rgb(rand6.nextInt(), rand6.nextInt(), rand6.nextInt());
        ArrayList<LatLng> directionPositionListLeft6 = leftRoute6.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight6 = rightRoute6.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft6, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight6, 5, Color.RED));


        Random rand7 = new Random();
        int directionThru7 = Color.rgb(rand7.nextInt(), rand7.nextInt(), rand7.nextInt());
        ArrayList<LatLng> directionPositionListLeft7 = leftRoute7.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        ArrayList<LatLng> directionPositionListRight7 = rightRoute7.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListLeft7, 5, Color.RED));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionListRight7, 5, Color.RED));

        collectionOfLeftRoutes.add(directionPositionListLeft1);
        collectionOfRightRoutes.add(directionPositionListRight1);
        collectionOfLeftRoutes.add(directionPositionListLeft2);
        collectionOfRightRoutes.add(directionPositionListRight2);
        collectionOfLeftRoutes.add(directionPositionListLeft3);
        collectionOfRightRoutes.add(directionPositionListRight3);
        collectionOfLeftRoutes.add(directionPositionListLeft4);
        collectionOfRightRoutes.add(directionPositionListRight4);
        collectionOfLeftRoutes.add(directionPositionListLeft5);
        collectionOfRightRoutes.add(directionPositionListRight5);
        collectionOfLeftRoutes.add(directionPositionListLeft6);
        collectionOfRightRoutes.add(directionPositionListRight6);
        collectionOfLeftRoutes.add(directionPositionListLeft7);
        collectionOfRightRoutes.add(directionPositionListRight7);


        determineSafestRoute();
    }

    public void determineSafestRoute() {
        double leftRoute1Result = getWolframResult(leftRoute1);
        double leftRoute2Result = getWolframResult(leftRoute2);
        double leftRoute3Result = getWolframResult(leftRoute3);
        double leftRoute4Result = getWolframResult(leftRoute4);
        double leftRoute5Result = getWolframResult(leftRoute5);
        double leftRoute6Result = getWolframResult(leftRoute6);
        double leftRoute7Result = getWolframResult(leftRoute7);

        double rightRoute1Result = getWolframResult(rightRoute1);
        double rightRoute2Result = getWolframResult(rightRoute2);
        double rightRoute3Result = getWolframResult(rightRoute3);
        double rightRoute4Result = getWolframResult(rightRoute4);
        double rightRoute5Result = getWolframResult(rightRoute5);
        double rightRoute6Result = getWolframResult(rightRoute6);
        double rightRoute7Result = getWolframResult(rightRoute7);

        double route1Total = leftRoute1Result + rightRoute1Result;
        double route2Total = leftRoute2Result + rightRoute2Result;
        double route3Total = leftRoute3Result + rightRoute3Result;
        double route4Total = leftRoute4Result + rightRoute4Result;
        double route5Total = leftRoute5Result + rightRoute5Result;
        double route6Total = leftRoute6Result + rightRoute6Result;
        double route7Total = leftRoute7Result + rightRoute7Result;

        double[] routes = new double[7];
        routes[0] = route1Total;
        routes[1] = route2Total;
        routes[2] = route3Total;
        routes[3] = route4Total;
        routes[4] = route5Total;
        routes[5] = route6Total;
        routes[6] = route7Total;

        int minIndex = 0;
        for (int i = 1; i < routes.length; i++) {
            double newnumber = routes[i];
            if ((newnumber < routes[minIndex])) {
                minIndex = i;
            }
        }

        ArrayList<LatLng> bestLeftRoute = collectionOfLeftRoutes.get(minIndex);
        ArrayList<LatLng> bestRightRoute = collectionOfRightRoutes.get(minIndex);

        googleMap.addPolyline(DirectionConverter.createPolyline(this, bestLeftRoute, 15, Color.GREEN));
        googleMap.addPolyline(DirectionConverter.createPolyline(this, bestRightRoute, 15, Color.GREEN));

        String formatedCrimes = getString(R.string.crime_data);

        for(int y = 0; y < crimeData.length; y++) {
            googleMap.addMarker(new MarkerOptions().position(new LatLng(crimeData[y][0], crimeData[y][1])).title("Crime"));
        }



    }

    public double getWolframResult(Direction inputDirection) {
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(this);
        final String formatedCrimes = getString(R.string.crime_data);

        String finalOutputString = "{";
        for(int j = 0; j < inputDirection.getRouteList().get(0).getLegList().get(0).getDirectionPoint().size(); j++) {
            finalOutputString += "{";
            double latIn = inputDirection.getRouteList().get(0).getLegList().get(0).getDirectionPoint().get(j).latitude;
            double longIn = inputDirection.getRouteList().get(0).getLegList().get(0).getDirectionPoint().get(j).longitude;
            finalOutputString += latIn + ",";

            if(inputDirection.getRouteList().get(0).getLegList().get(0).getDirectionPoint().size() - j - 1 == 0) {
                finalOutputString += longIn + "}";
            } else {
                finalOutputString += longIn + "},";
            }
        }
        finalOutputString += "}";
        Log.d("crimes", formatedCrimes);
        Log.d("output string", finalOutputString);

        String url = "https://www.wolframcloud.com/objects/63947c83-f29c-4a4d-b68d-7703c1547a9b";

        Log.d("final url", url);

        String encodedUrl = url;

        Log.d("Post Request", encodedUrl);
        returnedMeanValues = new ArrayList<JSONObject>();
        if(encodedUrl != null){
            final String finalOutputString1 = finalOutputString;
            StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response) {
                            // response
                            Log.d("Response", response);
                        }
                    },
                    new Response.ErrorListener()
                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // error
                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("route", finalOutputString1);

                    return params;
                }
            };
            queue.add(postRequest);
        }

        return 0.0;
    }

    public void generateWaypointLatLng(LatLng pointA, LatLng pointB) {
        double xA = pointA.longitude;
        double yA = pointA.latitude;
        double xB = pointB.longitude;
        double yB = pointB.latitude;

        for(int i = -3; i <= 3; i++) {

            double latGen = i*(((perpSlope(xA, xB, yA, yB) * distBetweenPoints(xA, xB, yA, yB) ) / ( 12 * Math.sqrt( Math.pow(perpSlope(xA, xB, yA, yB), 2) + 1 )))) + ((pointA.latitude + pointB.latitude)/2);
            double longGen = i*(69/53*(distBetweenPoints(xA, xB, yA, yB)/(12 * Math.sqrt(Math.pow(perpSlope(xA, xB, yA, yB), 2) + 1)))) + ((pointA.longitude + pointB.longitude)/2);

            LatLng waypoint = new LatLng(latGen, longGen);

            requestDirection(union, waypoint);
            requestDirection(waypoint, enterpriseWorks);
        }

    }

    public double distBetweenPoints(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(y2-y1, 2) + Math.pow(x2-x1, 2));
    }

    public double perpSlope(double x1, double x2, double y1, double y2) {
        Log.d("slope", (-1*(x2-x1))/(y2-y1) + "");
        return (-1*(x2-x1))/(y2-y1);
    }

    @Override
    public void onDirectionFailure(Throwable t) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        googleMap.addMarker(new MarkerOptions().position(new LatLng(union.latitude, union.longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        googleMap.addMarker(new MarkerOptions().position(new LatLng(enterpriseWorks.latitude, enterpriseWorks.longitude)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        //map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(enterpriseWorks, 12));
        generateWaypointLatLng(union, enterpriseWorks);
    }

    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
