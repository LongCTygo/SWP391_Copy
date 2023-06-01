<%-- 
    Document   : takequiz
    Created on : May 25, 2023, 5:13:59 PM
    Author     : admin
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/daisyui@2.51.6/dist/full.css" rel="stylesheet" type="text/css" />
        <script src="${pageContext.request.contextPath}/js/jquery-3.7.0.min.js"></script>
        <title>Quiz</title>
    </head>
    <body>
        <div class="alert alert-info bg-primary-content">
            <div>               
                <span class="font-mono text-2xl">1/20</span>
            </div>
        </div>

        <div class="footer items-center p-4 bg-info text-neutral-content">
            <div class="items-center grid-flow-col">
                <svg width="36" height="36" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" fill-rule="evenodd" clip-rule="evenodd" class="fill-current"><path d="M22.672 15.226l-2.432.811.841 2.515c.33 1.019-.209 2.127-1.23 2.456-1.15.325-2.148-.321-2.463-1.226l-.84-2.518-5.013 1.677.84 2.517c.391 1.203-.434 2.542-1.831 2.542-.88 0-1.601-.564-1.86-1.314l-.842-2.516-2.431.809c-1.135.328-2.145-.317-2.463-1.229-.329-1.018.211-2.127 1.231-2.456l2.432-.809-1.621-4.823-2.432.808c-1.355.384-2.558-.59-2.558-1.839 0-.817.509-1.582 1.327-1.846l2.433-.809-.842-2.515c-.33-1.02.211-2.129 1.232-2.458 1.02-.329 2.13.209 2.461 1.229l.842 2.515 5.011-1.677-.839-2.517c-.403-1.238.484-2.553 1.843-2.553.819 0 1.585.509 1.85 1.326l.841 2.517 2.431-.81c1.02-.33 2.131.211 2.461 1.229.332 1.018-.21 2.126-1.23 2.456l-2.433.809 1.622 4.823 2.433-.809c1.242-.401 2.557.484 2.557 1.838 0 .819-.51 1.583-1.328 1.847m-8.992-6.428l-5.01 1.675 1.619 4.828 5.011-1.674-1.62-4.829z"></path></svg> 
                <p class="font-mono text-2xl">Question: 1</p>
            </div> 
            <div class="grid-flow-col gap-4 md:place-self-center md:justify-self-end">
                <a>
                    <span class="font-mono text-2xl">
                        <span id="timer">00:00:00</span>
                    </span>
                </a> 
            </div>

        </div>

        <h1 class="font-mono text-2xl mb-2 ml-4">Trắc nghiệm một cách khách quan (tên giờ Anh là Objective test) là phương pháp dùng để chất vấn nhanh kiến thức, năng lực của người nào đó qua các thắc mắc đúng sai, câu hỏi lựa chọn giải đáp A, B, C để đánh giá. Một số các loại trắc nghiệm phổ biến.</h1>

        <div class="form-control ml-4">

            <input type="checkbox" class="checkbox checkbox-info" />
            <span class="label-text font-mono text-2xl">Ans A</span> 
        </div>  
        <div class="form-control ml-4">

            <input type="checkbox" class="checkbox checkbox-info" />
            <span class="label-text font-mono text-2xl">Ans B</span> 
        </div>        
        <div class="form-control ml-4">

            <input type="checkbox" class="checkbox checkbox-info" />
            <span class="label-text font-mono text-2xl">Ans C</span> 
        </div>
        <div class="form-control ml-4">

            <input type="checkbox" class="checkbox checkbox-info" />
            <span class="label-text font-mono text-2xl">Ans D</span> 
        </div>

        
        <div class="flex justify-end">
            <button class="btn btn-info mr-4">Review Progress</button>
        </div>


        <footer class="footer p-4 bg-base-300 text-base-content fixed bottom-0 w-full flex justify-between">  
            <div>
                <button class="btn btn-active">Previous</button>
            </div>
            <div>
                <button class="btn btn-active">Next</button>
            </div>
        </footer>

        <script src="https://cdn.tailwindcss.com"></script>
        <script>
            <%
            String timeout = (String) request.getAttribute("timeout");
            if (timeout == null){
                //TODO
                timeout = "May 30, 2023 10:12:00"; //Use this format
            }
            %>
            console.log("<%=timeout%>");
            let timeup = new Date("<%=timeout%>").getTime();
            let counter = setInterval(timer, 1000);

            function timer() {
                let now = new Date().getTime();
                if (now > timeup) {
                    //TODO Timer Expiry
                    window.location = "/logout"; //or somethin'
                    clearInterval(counter);
                    return;
                }
                let count = Math.round((timeup - now) / 1000);
                let hours = Math.floor(count / 3600);
                count -= hours * 3600;
                let minutes = Math.floor(count / 60)
                count -= minutes * 60;
                let seconds = count;
                let timerElement = $("#timer");
                timerElement.html(getFormattedTime(hours,minutes,seconds));
                if (minutes < 5){
                    timerElement.addClass("text-red-600");
                }
            }
        </script>
    </body>
    <script>
        function pad(num, size) {
            num = num.toString();
            while (num.length < size) num = "0" + num;
            return num;
        }

        function getFormattedTime(hours, minutes, seconds){
            let formattedTime = ""
            if (hours > 0){
                formattedTime += pad(hours,2) + ":"
            }
            formattedTime += pad(minutes,2) + ":" + pad(seconds,2)
            return formattedTime;
        }
    </script>
</html>
