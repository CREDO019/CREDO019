package com.onesignal;

import com.onesignal.FocusTimeController;
import com.onesignal.influence.domain.OSInfluence;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class OSFocusTimeProcessorFactory {
    private final HashMap<String, FocusTimeController.FocusTimeProcessorBase> focusTimeProcessors;

    public OSFocusTimeProcessorFactory() {
        HashMap<String, FocusTimeController.FocusTimeProcessorBase> hashMap = new HashMap<>();
        this.focusTimeProcessors = hashMap;
        hashMap.put(FocusTimeController.FocusTimeProcessorUnattributed.class.getName(), new FocusTimeController.FocusTimeProcessorUnattributed());
        hashMap.put(FocusTimeController.FocusTimeProcessorAttributed.class.getName(), new FocusTimeController.FocusTimeProcessorAttributed());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FocusTimeController.FocusTimeProcessorBase getTimeProcessorSaved() {
        FocusTimeController.FocusTimeProcessorBase attributedProcessor = getAttributedProcessor();
        for (OSInfluence oSInfluence : attributedProcessor.getInfluences()) {
            if (oSInfluence.getInfluenceType().isAttributed()) {
                return attributedProcessor;
            }
        }
        return getUnattributedProcessor();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FocusTimeController.FocusTimeProcessorBase getTimeProcessorWithInfluences(List<OSInfluence> list) {
        boolean z;
        Iterator<OSInfluence> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            } else if (it.next().getInfluenceType().isAttributed()) {
                z = true;
                break;
            }
        }
        if (z) {
            return getAttributedProcessor();
        }
        return getUnattributedProcessor();
    }

    private FocusTimeController.FocusTimeProcessorBase getAttributedProcessor() {
        return this.focusTimeProcessors.get(FocusTimeController.FocusTimeProcessorAttributed.class.getName());
    }

    private FocusTimeController.FocusTimeProcessorBase getUnattributedProcessor() {
        return this.focusTimeProcessors.get(FocusTimeController.FocusTimeProcessorUnattributed.class.getName());
    }
}
