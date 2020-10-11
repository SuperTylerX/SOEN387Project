<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Group Chat</title>
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/bootstrap-material-design.min.css">
    <link rel="stylesheet" href="./css/fontawesome-5.14.0.min.css">
    <%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("theme") && cookies[i].getValue().equals("dark")) {
    %>
    <link rel="stylesheet" href="./css/dark.css">
    <%
                }
            }
        }
    %>


</head>

<body>
<div class="chat-container">
    <div class="left-pannel">
        <div class="top-info" id="user-area">
            <img src="./images/avatar.png" alt="" srcset="">
            <span id="username">Anonymous</span>
        </div>

        <ul class="menu">
            <li id="theme-btn"><i class="fas fa-paint-brush"></i>Theme</li>
            <li id="refresh-btn"><i class="fas fa-redo-alt"></i>Refresh</li>
            <li id="download-btn"><i class="fas fa-download"></i>Download</li>
            <li id="clear-btn"><i class="fas fa-trash-alt"></i>Clear</li>
        </ul>

        <div class="bottom-bar">
            <div>Team Member: Tianxiang Ying, Bowen Yang,</div>
            <div>Yanqi Zhang, Xuan Li</div>
        </div>
    </div>
    <div class="right-main-box">
        <div class="top-bar">
            <i class="fas fa-bars" id="bar-btn"></i> Group Chat
        </div>
        <div class="chatting-list">

            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
            <c:forEach items="${messageList}" var="item">
                <div class="chat-message">
                    <div class="chat-avatar" style="background-image: url(./images/avatar.png);"></div>
                    <div class="chat-content">
                        <div class="chat-content-info">
                            <div class="chat-content-meta">
                                <span class="chat-name">${item.username== null ? "Anonymous" : item.username}</span>
                                <span class="chat-time">${item.formatDate}</span>
                            </div>
                        </div>
                        <div class="chat-content-text">
                                ${item.content}
                        </div>

                    </div>
                </div>
            </c:forEach>

            <div class="typing-box">
                <form action="./post" method="POST" id="chatting-form">
                    <input type="hidden" name="username" value="" id="username-hidden-input">
                    <input type="text" class="form-control" placeholder="Say Something..." name="message"
                           autocomplete="off" id="message-input">
                    <button type="submit" class="btn btn-primary active btn-sm" id="send-btn" disabled="disabled">Send
                    </button>
                </form>
            </div>
        </div>
    </div>


    <!-- Modal For UserName -->
    <div class="modal fade" id="username_modal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Username Setting</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <label for="username-input">UserName</label>
                            <input type="text" class="form-control" id="username-input">
                        </div>
                        <div class="switch">
                            <label>
                                <input type="checkbox" id="anonymous-switch">Anonymous
                            </label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="username-save-btn">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal For Theme -->
    <div class="modal fade" id="theme_modal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Theme Setting</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="radio">
                            <label>
                                <input type="radio" id="light" name="theme" value="light">Light
                            </label>
                        </div>
                        <div class="radio">
                            <label>
                                <input type="radio" id="dark" name="theme" value="dark">Dark
                            </label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="theme-save-btn">Save changes</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal For Download -->
    <div class="modal fade time-modal" id="download_modal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Download Chatting History</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form>
                        <label> From:
                            <input type="datetime-local" id="download-from" name="download-from" class="datetime">
                        </label>

                        <label> To:
                            <input type="datetime-local" id="download-to" name="download-to" class="datetime">
                        </label>

                        <div class="radio" id="download-format">
                            <label>
                                <input type="radio" name="format" value="xml" checked="checked">XML
                            </label>
                            <label>
                                <input type="radio" name="format" value="text">Plain Text
                            </label>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="download-save-btn">Download</button>
                </div>
            </div>
        </div>
    </div>


    <!-- Modal For Clear -->
    <div class="modal fade time-modal" id="clear_modal" tabindex="-1" role="dialog">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Clear Chatting History</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="clear-form" method="POST" action="./delete">
                        <label> From:
                            <input type="datetime-local" id="clear-from" name="clear-from" class="datetime">
                        </label>

                        <label> To:
                            <input type="datetime-local" id="clear-to" name="clear-to" class="datetime">
                        </label>

                        <input type="hidden" name="from" id="from-hidden-input">
                        <input type="hidden" name="to" id="to-hidden-input">

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary" id="clear-save-btn">Clear</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>


    <script src="./js/jquery-3.2.1.slim.min.js"></script>
    <script src="./js/popper.js"></script>
    <script src="./js/bootstrap-material-design.js"></script>
    <script src="./js/main.js"></script>
</body>

</html>