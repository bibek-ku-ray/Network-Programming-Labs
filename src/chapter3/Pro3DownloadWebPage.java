package chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Pro3DownloadWebPage {
    public static void main(String[] args) throws IOException {
        String host = "https://deerwalk.edu.np/";

        URL url = new URL(host);

        URLConnection urlConnection = url.openConnection();
        InputStream inputStream = urlConnection.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while((line = reader.readLine())!=null){
            System.out.println(line);
        }
        reader.close();
    }
}

/*
Output:
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Google tag (gtag.js) -->
<script async src="https://www.googletagmanager.com/gtag/js?id=G-GMHS6MTZEM"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());

  gtag('config', 'G-GMHS6MTZEM');
</script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta property="og:image" content="http://deerwalk.edu.np/images/ogBackground.jpg"/>
    <meta property="og:url" content="http://deerwalk.edu.np/" />
    <meta property="og:title" content="Deerwalk Education Group" />
    <meta property="og:description" content="Deerwalk Group Limited is a limited company that runs Deerwalk Sifal School (1-12 School) and Deerwalk Institute of Technology (Undergraduate Programs - BCA/CSIT)." />

    <title>Deerwalk Education Group</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel='shortcut icon' type='image/x-icon' href='./images/logoOnly.png' />
    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">
    <link href="css/progresscircle.css" rel="stylesheet">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://use.fontawesome.com/releases/v5.0.8/js/all.js"></script>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="style.css" />
</head>

<body>

    <!-- navbar -->
    <nav class="navbar navbar-light bg-blue">

        <img src="images/deg-logo.png" class="image-fluid img-center" height="60">
        </nav>
        <div class="row no-gutters">
            <div class="col-3 green"></div>
            <div class="col-3 orange"></div>
            <div class="col-3 white"></div>
            <div class="col-3 brown"></div>
            </div>
   <!-- h -->
    <!-- container -->

    <div class="container-fluid">
        <div class="row">
            <div class="container-fluid body-height mt-5 body">
                <div class="row justify-content-center">
                    <div class="col-md-3 bg-overlayBox-1 mr-3 mx-0-768">
                     <a href="sifalschool" target="_blank">
                     <div class="inner-div">
                         <div class="inner-div-overlay">
                            <div class="row text-center pt-5 justify-content-center">
                                <div class="col-12 ">
                                <img src="./images/logoOnly.png" height="80">
                                </div>
                                <div class="col-11 text-white mt-3 pb-2 border-bottom">
                                <span class="title">Deerwalk Sifal School</span>
                                </div>
                                </div>

                                <div class="row text-center mt-4 text-white">
                                    <div class="col-12">
                                    <div class="col-12">
                                    <p class="title-font"><span>Elementary School<br><br> Middle School <br><br> High School</span></p>
                                    </div>
                                    </div>
                                    </div>
                                </div>
                        </div>
                    </a>
                </div>

                <div class="col-md-3 bg-overlayBox-3 mr-3 mx-0-768">
                    <a href="dhading-school" target="_blank">
                    <div class="inner-div-3">
                        <div class="inner-div-overlay-3">

                            <div class="row text-center pt-5 justify-content-center">
                                <div class="col-12 ">
                                    <img src="./images/logoOnly.png" height="80">
                                </div>

                                <div class="col-11 text-white mt-3 pb-2 border-bottom">
                                    <span class="title">Deerwalk Dhading School</span>
                                </div>
                            </div>

                            <div class="row text-center mt-4 text-white list">
                                <div class="col-12">
                                    <p class="title-font"><span class="list">Junior School<br><br>Senior School</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    </a>
                </div>

                <div class="col-md-3 bg-overlayBox-2 mr-3 mx-0-768">
                    <a href="DWIT" target="_blank">
                    <div class="inner-div-1">
                        <div class="inner-div-overlay-1">

                            <div class="row text-center pt-5 justify-content-center">
                                <div class="col-12 ">
                                    <img src="./images/logoOnly.png" height="80">
                                </div>

                                <div class="col-11 text-white mt-3 pb-2 border-bottom">
                                    <span class="title">Deerwalk Institute of Technology</span>
                                </div>
                            </div>

                            <div class="row text-center mt-4 text-white list">
                                <div class="col-12">
                                    <p class="title-font"><span class="list">B.Sc.CSIT<br><br>Bachelor in Computer Applications<br><br>Diploma In US Healthcare Data Analytics</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    </a>
                </div>
            </div>
        </div>

    </div>
</div>
<!--
    <nav class="nav-bar navbar-light bg-white">
       <p style="text-align: center; color: #0f5288;" class="mt-5">2021, Deerwalk Education group- Version 2.0.0</p>
        </nav> -->
        <!-- bottom nav -->

        <div class="row no-gutters" style="margin-top: 150px;">
            <p class="m-auto bg-tns-black text-white footer">© <span id="year">2</span> Deerwalk Education Group - Version 2.0.0</p>
            </div>

        <script type="text/javascript">
            console.log(document.getElementById('year').innerText = new Date().getFullYear());
        </script>
    </html>


 */
