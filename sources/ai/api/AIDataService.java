package ai.api;

import ai.api.http.HttpClient;
import ai.api.model.AIContext;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Entity;
import ai.api.model.Status;
import ai.api.util.IOUtils;
import ai.api.util.StringUtils;
import androidx.browser.trusted.sharing.ShareTarget;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpRetryException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/* loaded from: classes.dex */
public class AIDataService {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String DEFAULT_REQUEST_METHOD = "POST";
    private static final String REQUEST_METHOD_DELETE = "DELETE";
    private static final String REQUEST_METHOD_GET = "GET";
    private static final String REQUEST_METHOD_POST = "POST";
    private final AIConfiguration config;
    private final AIServiceContext defaultServiceContext;
    private static final Logger Log = LogManager.getLogger((Class<?>) AIDataService.class);
    private static final AIServiceContext UNDEFINED_SERVICE_CONTEXT = null;
    private static final Gson GSON = GsonFactory.getDefaultFactory().getGson();

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public interface ApiActiveContextListResponse extends List<AIContext> {
    }

    public AIDataService(AIConfiguration aIConfiguration, AIServiceContext aIServiceContext) {
        if (aIConfiguration == null) {
            throw new IllegalArgumentException("config should not be null");
        }
        this.config = aIConfiguration.m1430clone();
        if (aIServiceContext == null) {
            this.defaultServiceContext = new AIServiceContextBuilder().generateSessionId().build();
        } else {
            this.defaultServiceContext = aIServiceContext;
        }
    }

    public AIDataService(AIConfiguration aIConfiguration) {
        this(aIConfiguration, null);
    }

    public AIServiceContext getContext() {
        return this.defaultServiceContext;
    }

    public AIResponse request(AIRequest aIRequest) throws AIServiceException {
        return request(aIRequest, (RequestExtras) null);
    }

    public AIResponse request(AIRequest aIRequest, AIServiceContext aIServiceContext) throws AIServiceException {
        return request(aIRequest, null, aIServiceContext);
    }

    public AIResponse request(AIRequest aIRequest, RequestExtras requestExtras) throws AIServiceException {
        return request(aIRequest, requestExtras, UNDEFINED_SERVICE_CONTEXT);
    }

    public AIResponse request(AIRequest aIRequest, RequestExtras requestExtras, AIServiceContext aIServiceContext) throws AIServiceException {
        if (aIRequest == null) {
            throw new IllegalArgumentException("Request argument must not be null");
        }
        Logger logger = Log;
        logger.debug("Start request");
        try {
            aIRequest.setLanguage(this.config.getApiAiLanguage());
            aIRequest.setSessionId(getSessionId(aIServiceContext));
            if (StringUtils.isEmpty(aIRequest.getTimezone())) {
                aIRequest.setTimezone(getTimeZone(aIServiceContext));
            }
            Map<String, String> map = null;
            if (requestExtras != null) {
                fillRequest(aIRequest, requestExtras);
                map = requestExtras.getAdditionalHeaders();
            }
            Gson gson = GSON;
            String doTextRequest = doTextRequest(this.config.getQuestionUrl(getSessionId(aIServiceContext)), gson.toJson(aIRequest), map);
            if (StringUtils.isEmpty(doTextRequest)) {
                throw new AIServiceException("Empty response from ai service. Please check configuration and Internet connection.");
            }
            logger.debug("Response json: " + doTextRequest.replaceAll("[\r\n]+", " "));
            AIResponse aIResponse = (AIResponse) gson.fromJson(doTextRequest, (Class<Object>) AIResponse.class);
            if (aIResponse == null) {
                throw new AIServiceException("API.AI response parsed as null. Check debug log for details.");
            }
            if (aIResponse.isError()) {
                throw new AIServiceException(aIResponse);
            }
            aIResponse.cleanup();
            return aIResponse;
        } catch (JsonSyntaxException e) {
            throw new AIServiceException("Wrong service answer format. Please, connect to API.AI Service support", e);
        } catch (MalformedURLException e2) {
            Log.error("Malformed url should not be raised", (Throwable) e2);
            throw new AIServiceException("Wrong configuration. Please, connect to API.AI Service support", e2);
        }
    }

    public AIResponse voiceRequest(InputStream inputStream) throws AIServiceException {
        return voiceRequest(inputStream, new RequestExtras());
    }

    public AIResponse voiceRequest(InputStream inputStream, List<AIContext> list) throws AIServiceException {
        return voiceRequest(inputStream, new RequestExtras(list, null));
    }

    public AIResponse voiceRequest(InputStream inputStream, RequestExtras requestExtras) throws AIServiceException {
        return voiceRequest(inputStream, requestExtras, UNDEFINED_SERVICE_CONTEXT);
    }

    public AIResponse voiceRequest(InputStream inputStream, RequestExtras requestExtras, AIServiceContext aIServiceContext) throws AIServiceException {
        Logger logger = Log;
        logger.debug("Start voice request");
        try {
            AIRequest aIRequest = new AIRequest();
            aIRequest.setLanguage(this.config.getApiAiLanguage());
            aIRequest.setSessionId(getSessionId(aIServiceContext));
            aIRequest.setTimezone(getTimeZone(aIServiceContext));
            Map<String, String> map = null;
            if (requestExtras != null) {
                fillRequest(aIRequest, requestExtras);
                map = requestExtras.getAdditionalHeaders();
            }
            Gson gson = GSON;
            String json = gson.toJson(aIRequest);
            logger.debug("Request json: " + json);
            String doSoundRequest = doSoundRequest(inputStream, json, map);
            if (StringUtils.isEmpty(doSoundRequest)) {
                throw new AIServiceException("Empty response from ai service. Please check configuration.");
            }
            logger.debug("Response json: " + doSoundRequest);
            AIResponse aIResponse = (AIResponse) gson.fromJson(doSoundRequest, (Class<Object>) AIResponse.class);
            if (aIResponse == null) {
                throw new AIServiceException("API.AI response parsed as null. Check debug log for details.");
            }
            if (aIResponse.isError()) {
                throw new AIServiceException(aIResponse);
            }
            aIResponse.cleanup();
            return aIResponse;
        } catch (JsonSyntaxException e) {
            throw new AIServiceException("Wrong service answer format. Please, connect to API.AI Service support", e);
        } catch (MalformedURLException e2) {
            Log.error("Malformed url should not be raised", (Throwable) e2);
            throw new AIServiceException("Wrong configuration. Please, connect to AI Service support", e2);
        }
    }

    public boolean resetContexts() {
        AIRequest aIRequest = new AIRequest();
        aIRequest.setQuery("empty_query_for_resetting_contexts");
        aIRequest.setResetContexts(true);
        try {
            return !request(aIRequest).isError();
        } catch (AIServiceException e) {
            Log.error("Exception while contexts clean.", (Throwable) e);
            return false;
        }
    }

    public List<AIContext> getActiveContexts() throws AIServiceException {
        return getActiveContexts(UNDEFINED_SERVICE_CONTEXT);
    }

    public List<AIContext> getActiveContexts(AIServiceContext aIServiceContext) throws AIServiceException {
        try {
            return (List) doRequest(ApiActiveContextListResponse.class, this.config.getContextsUrl(getSessionId(aIServiceContext)), "GET");
        } catch (BadResponseStatusException e) {
            throw new AIServiceException(e.response);
        }
    }

    public AIContext getActiveContext(String str) throws AIServiceException {
        return getActiveContext(str, UNDEFINED_SERVICE_CONTEXT);
    }

    public AIContext getActiveContext(String str, AIServiceContext aIServiceContext) throws AIServiceException {
        try {
            return (AIContext) doRequest(AIContext.class, this.config.getContextsUrl(getSessionId(aIServiceContext), str), "GET");
        } catch (BadResponseStatusException e) {
            if (e.response.getStatus().getCode().intValue() == 404) {
                return null;
            }
            throw new AIServiceException(e.response);
        }
    }

    public List<String> addActiveContext(Iterable<AIContext> iterable) throws AIServiceException {
        return addActiveContext(iterable, UNDEFINED_SERVICE_CONTEXT);
    }

    public List<String> addActiveContext(Iterable<AIContext> iterable, AIServiceContext aIServiceContext) throws AIServiceException {
        try {
            return ((ApiActiveContextNamesResponse) doRequest((AIDataService) iterable, ApiActiveContextNamesResponse.class, this.config.getContextsUrl(getSessionId(aIServiceContext)), ShareTarget.METHOD_POST)).names;
        } catch (BadResponseStatusException e) {
            throw new AIServiceException(e.response);
        }
    }

    public String addActiveContext(AIContext aIContext) throws AIServiceException {
        return addActiveContext(aIContext, UNDEFINED_SERVICE_CONTEXT);
    }

    public String addActiveContext(AIContext aIContext, AIServiceContext aIServiceContext) throws AIServiceException {
        try {
            ApiActiveContextNamesResponse apiActiveContextNamesResponse = (ApiActiveContextNamesResponse) doRequest((AIDataService) aIContext, ApiActiveContextNamesResponse.class, this.config.getContextsUrl(getSessionId(aIServiceContext)), ShareTarget.METHOD_POST);
            if (apiActiveContextNamesResponse.names == null || apiActiveContextNamesResponse.names.size() <= 0) {
                return null;
            }
            return apiActiveContextNamesResponse.names.get(0);
        } catch (BadResponseStatusException e) {
            throw new AIServiceException(e.response);
        }
    }

    public void resetActiveContexts() throws AIServiceException {
        resetActiveContexts(UNDEFINED_SERVICE_CONTEXT);
    }

    public void resetActiveContexts(AIServiceContext aIServiceContext) throws AIServiceException {
        try {
            doRequest(AIResponse.class, this.config.getContextsUrl(getSessionId(aIServiceContext)), REQUEST_METHOD_DELETE);
        } catch (BadResponseStatusException e) {
            throw new AIServiceException(e.response);
        }
    }

    public boolean removeActiveContext(String str) throws AIServiceException {
        return removeActiveContext(str, UNDEFINED_SERVICE_CONTEXT);
    }

    public boolean removeActiveContext(String str, AIServiceContext aIServiceContext) throws AIServiceException {
        try {
            doRequest(AIResponse.class, this.config.getContextsUrl(getSessionId(aIServiceContext), str), REQUEST_METHOD_DELETE);
            return true;
        } catch (BadResponseStatusException e) {
            if (e.response.getStatus().getCode().intValue() == 404) {
                return false;
            }
            throw new AIServiceException(e.response);
        }
    }

    public AIResponse uploadUserEntity(Entity entity) throws AIServiceException {
        return uploadUserEntity(entity, UNDEFINED_SERVICE_CONTEXT);
    }

    public AIResponse uploadUserEntity(Entity entity, AIServiceContext aIServiceContext) throws AIServiceException {
        return uploadUserEntities(Collections.singleton(entity), aIServiceContext);
    }

    public AIResponse uploadUserEntities(Collection<Entity> collection) throws AIServiceException {
        return uploadUserEntities(collection, UNDEFINED_SERVICE_CONTEXT);
    }

    public AIResponse uploadUserEntities(Collection<Entity> collection, AIServiceContext aIServiceContext) throws AIServiceException {
        if (collection == null || collection.size() == 0) {
            throw new AIServiceException("Empty entities list");
        }
        Gson gson = GSON;
        try {
            String doTextRequest = doTextRequest(this.config.getUserEntitiesEndpoint(getSessionId(aIServiceContext)), gson.toJson(collection));
            if (StringUtils.isEmpty(doTextRequest)) {
                throw new AIServiceException("Empty response from ai service. Please check configuration and Internet connection.");
            }
            Logger logger = Log;
            logger.debug("Response json: " + doTextRequest);
            AIResponse aIResponse = (AIResponse) gson.fromJson(doTextRequest, (Class<Object>) AIResponse.class);
            if (aIResponse == null) {
                throw new AIServiceException("API.AI response parsed as null. Check debug log for details.");
            }
            if (aIResponse.isError()) {
                throw new AIServiceException(aIResponse);
            }
            aIResponse.cleanup();
            return aIResponse;
        } catch (JsonSyntaxException e) {
            throw new AIServiceException("Wrong service answer format. Please, connect to API.AI Service support", e);
        } catch (MalformedURLException e2) {
            Log.error("Malformed url should not be raised", (Throwable) e2);
            throw new AIServiceException("Wrong configuration. Please, connect to AI Service support", e2);
        }
    }

    protected String doTextRequest(String str, AIServiceContext aIServiceContext) throws MalformedURLException, AIServiceException {
        return doTextRequest(this.config.getQuestionUrl(getSessionId(aIServiceContext)), str);
    }

    protected String doTextRequest(String str) throws MalformedURLException, AIServiceException {
        return doTextRequest(str, UNDEFINED_SERVICE_CONTEXT);
    }

    protected String doTextRequest(String str, String str2) throws MalformedURLException, AIServiceException {
        return doTextRequest(str, str2, null);
    }

    protected String doTextRequest(String str, String str2, Map<String, String> map) throws MalformedURLException, AIServiceException {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                URL url = new URL(str);
                Logger logger = Log;
                logger.debug("Request json: " + str2);
                if (this.config.getProxy() != null) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(this.config.getProxy());
                } else {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                }
                httpURLConnection2 = httpURLConnection;
                httpURLConnection2.setRequestMethod(ShareTarget.METHOD_POST);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.addRequestProperty("Authorization", "Bearer " + this.config.getApiKey());
                httpURLConnection2.addRequestProperty("Content-Type", "application/json; charset=utf-8");
                httpURLConnection2.addRequestProperty("Accept", "application/json");
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection2.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection2.connect();
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection2.getOutputStream());
                IOUtils.writeAll(str2, bufferedOutputStream);
                bufferedOutputStream.close();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection2.getInputStream());
                String readAll = IOUtils.readAll(bufferedInputStream);
                bufferedInputStream.close();
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                return readAll;
            } catch (IOException e) {
                if (httpURLConnection2 != null) {
                    try {
                        InputStream errorStream = httpURLConnection2.getErrorStream();
                        if (errorStream != null) {
                            String readAll2 = IOUtils.readAll(errorStream);
                            Log.debug(readAll2);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            return readAll2;
                        }
                        throw new AIServiceException("Can't connect to the api.ai service.", e);
                    } catch (IOException e2) {
                        Log.warn("Can't read error response", (Throwable) e2);
                        Log.error("Can't make request to the API.AI service. Please, check connection settings and API access token.", (Throwable) e);
                        throw new AIServiceException("Can't make request to the API.AI service. Please, check connection settings and API access token.", e);
                    }
                }
                Log.error("Can't make request to the API.AI service. Please, check connection settings and API access token.", (Throwable) e);
                throw new AIServiceException("Can't make request to the API.AI service. Please, check connection settings and API access token.", e);
            }
        } catch (Throwable th) {
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    protected String doSoundRequest(InputStream inputStream, String str) throws MalformedURLException, AIServiceException {
        return doSoundRequest(inputStream, str, null, UNDEFINED_SERVICE_CONTEXT);
    }

    protected String doSoundRequest(InputStream inputStream, String str, Map<String, String> map) throws MalformedURLException, AIServiceException {
        return doSoundRequest(inputStream, str, map, UNDEFINED_SERVICE_CONTEXT);
    }

    protected String doSoundRequest(InputStream inputStream, String str, Map<String, String> map, AIServiceContext aIServiceContext) throws MalformedURLException, AIServiceException {
        HttpURLConnection httpURLConnection;
        HttpClient httpClient;
        HttpURLConnection httpURLConnection2 = null;
        r1 = null;
        HttpClient httpClient2 = null;
        try {
            URL url = new URL(this.config.getQuestionUrl(getSessionId(aIServiceContext)));
            Log.debug("Connecting to {}", url);
            if (this.config.getProxy() != null) {
                httpURLConnection = (HttpURLConnection) url.openConnection(this.config.getProxy());
            } else {
                httpURLConnection = (HttpURLConnection) url.openConnection();
            }
        } catch (IOException e) {
            e = e;
            httpURLConnection = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            try {
                httpURLConnection.addRequestProperty("Authorization", "Bearer " + this.config.getApiKey());
                httpURLConnection.addRequestProperty("Accept", "application/json");
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection.setRequestMethod(ShareTarget.METHOD_POST);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                httpClient = new HttpClient(httpURLConnection);
            } catch (IOException e2) {
                e = e2;
            }
            try {
                httpClient.setWriteSoundLog(this.config.isWriteSoundLog());
                httpClient.connectForMultipart();
                httpClient.addFormPart("request", str);
                httpClient.addFilePart("voiceData", "voice.wav", inputStream);
                httpClient.finishMultipart();
                String response = httpClient.getResponse();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return response;
            } catch (IOException e3) {
                e = e3;
                httpClient2 = httpClient;
                if (httpClient2 != null) {
                    String errorString = httpClient2.getErrorString();
                    Log.debug(errorString);
                    if (!StringUtils.isEmpty(errorString)) {
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return errorString;
                    } else if (e instanceof HttpRetryException) {
                        AIResponse aIResponse = new AIResponse();
                        Status fromResponseCode = Status.fromResponseCode(((HttpRetryException) e).responseCode());
                        fromResponseCode.setErrorDetails(((HttpRetryException) e).getReason());
                        aIResponse.setStatus(fromResponseCode);
                        throw new AIServiceException(aIResponse);
                    }
                }
                Log.error("Can't make request to the API.AI service. Please, check connection settings and API.AI keys.", (Throwable) e);
                throw new AIServiceException("Can't make request to the API.AI service. Please, check connection settings and API.AI keys.", e);
            }
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection2 = httpURLConnection;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    protected <TResponse> TResponse doRequest(Type type, String str, String str2) throws AIServiceException, BadResponseStatusException {
        return (TResponse) doRequest(type, str, str2, (Map<String, String>) null);
    }

    protected <TRequest, TResponse> TResponse doRequest(TRequest trequest, Type type, String str, String str2) throws AIServiceException, BadResponseStatusException {
        return (TResponse) doRequest(trequest, type, str, str2, null);
    }

    protected <TResponse> TResponse doRequest(Type type, String str, String str2, Map<String, String> map) throws AIServiceException, BadResponseStatusException {
        return (TResponse) doRequest(null, type, str, str2, map);
    }

    protected <TRequest, TResponse> TResponse doRequest(TRequest trequest, Type type, String str, String str2, Map<String, String> map) throws AIServiceException, BadResponseStatusException {
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnection2 = null;
        try {
            try {
                URL url = new URL(str);
                String json = trequest != null ? GSON.toJson(trequest) : null;
                if (str2 == null) {
                    str2 = ShareTarget.METHOD_POST;
                }
                Logger logger = Log;
                logger.debug("Request json: " + json);
                if (this.config.getProxy() != null) {
                    httpURLConnection = (HttpURLConnection) url.openConnection(this.config.getProxy());
                } else {
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                }
                HttpURLConnection httpURLConnection3 = httpURLConnection;
                if (json != null && !ShareTarget.METHOD_POST.equals(str2)) {
                    throw new AIServiceException("Non-empty request should be sent using POST method");
                }
                httpURLConnection3.setRequestMethod(str2);
                if (ShareTarget.METHOD_POST.equals(str2)) {
                    httpURLConnection3.setDoOutput(true);
                }
                httpURLConnection3.addRequestProperty("Authorization", "Bearer " + this.config.getApiKey());
                httpURLConnection3.addRequestProperty("Content-Type", "application/json; charset=utf-8");
                httpURLConnection3.addRequestProperty("Accept", "application/json");
                if (map != null) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        httpURLConnection3.addRequestProperty(entry.getKey(), entry.getValue());
                    }
                }
                httpURLConnection3.connect();
                if (json != null) {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection3.getOutputStream());
                    IOUtils.writeAll(json, bufferedOutputStream);
                    bufferedOutputStream.close();
                }
                BufferedInputStream bufferedInputStream = new BufferedInputStream(httpURLConnection3.getInputStream());
                String readAll = IOUtils.readAll(bufferedInputStream);
                bufferedInputStream.close();
                try {
                    AIResponse aIResponse = (AIResponse) GSON.fromJson(readAll, (Class<Object>) AIResponse.class);
                    if (aIResponse.getStatus() != null && aIResponse.getStatus().getCode().intValue() != 200) {
                        throw new BadResponseStatusException(aIResponse);
                    }
                } catch (JsonParseException unused) {
                }
                TResponse tresponse = (TResponse) GSON.fromJson(readAll, type);
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return tresponse;
            } catch (IOException e) {
                if (0 != 0) {
                    try {
                        InputStream errorStream = httpURLConnection2.getErrorStream();
                        if (errorStream != null) {
                            String readAll2 = IOUtils.readAll(errorStream);
                            Log.debug(readAll2);
                            throw new AIServiceException(readAll2, e);
                        }
                        throw new AIServiceException("Can't connect to the api.ai service.", e);
                    } catch (IOException e2) {
                        Log.warn("Can't read error response", (Throwable) e2);
                        Log.error("Can't make request to the API.AI service. Please, check connection settings and API access token.", (Throwable) e);
                        throw new AIServiceException("Can't make request to the API.AI service. Please, check connection settings and API access token.", e);
                    }
                }
                Log.error("Can't make request to the API.AI service. Please, check connection settings and API access token.", (Throwable) e);
                throw new AIServiceException("Can't make request to the API.AI service. Please, check connection settings and API access token.", e);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    private void fillRequest(AIRequest aIRequest, RequestExtras requestExtras) {
        if (requestExtras.hasContexts()) {
            aIRequest.setContexts(requestExtras.getContexts());
        }
        if (requestExtras.hasEntities()) {
            aIRequest.setEntities(requestExtras.getEntities());
        }
        if (requestExtras.getLocation() != null) {
            aIRequest.setLocation(requestExtras.getLocation());
        }
    }

    private String getSessionId(AIServiceContext aIServiceContext) {
        if (aIServiceContext != null) {
            return aIServiceContext.getSessionId();
        }
        return this.defaultServiceContext.getSessionId();
    }

    private String getTimeZone(AIServiceContext aIServiceContext) {
        TimeZone timeZone;
        if (aIServiceContext != null) {
            timeZone = aIServiceContext.getTimeZone();
        } else {
            timeZone = this.defaultServiceContext.getTimeZone();
        }
        if (timeZone == null) {
            timeZone = Calendar.getInstance().getTimeZone();
        }
        return timeZone.getID();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class ApiActiveContextNamesResponse extends AIResponse {
        private static final long serialVersionUID = 1;
        public List<String> names;

        private ApiActiveContextNamesResponse() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class BadResponseStatusException extends Exception {
        private static final long serialVersionUID = 1;
        private final AIResponse response;

        public BadResponseStatusException(AIResponse aIResponse) {
            this.response = aIResponse;
        }
    }
}
