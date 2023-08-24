package com.facebook.react.fabric.mounting.mountitems;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.fabric.FabricComponents;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.systrace.Systrace;

/* loaded from: classes.dex */
public class IntBufferBatchMountItem implements MountItem {
    static final int INSTRUCTION_CREATE = 2;
    static final int INSTRUCTION_DELETE = 4;
    static final int INSTRUCTION_FLAG_MULTIPLE = 1;
    static final int INSTRUCTION_INSERT = 8;
    static final int INSTRUCTION_REMOVE = 16;
    static final int INSTRUCTION_REMOVE_DELETE_TREE = 2048;
    static final int INSTRUCTION_UPDATE_EVENT_EMITTER = 256;
    static final int INSTRUCTION_UPDATE_LAYOUT = 128;
    static final int INSTRUCTION_UPDATE_OVERFLOW_INSET = 1024;
    static final int INSTRUCTION_UPDATE_PADDING = 512;
    static final int INSTRUCTION_UPDATE_PROPS = 32;
    static final int INSTRUCTION_UPDATE_STATE = 64;
    static final String TAG = "IntBufferBatchMountItem";
    private final int mCommitNumber;
    private final int[] mIntBuffer;
    private final int mIntBufferLen;
    private final Object[] mObjBuffer;
    private final int mObjBufferLen;
    private final int mSurfaceId;

    public IntBufferBatchMountItem(int r1, int[] r2, Object[] objArr, int r4) {
        this.mSurfaceId = r1;
        this.mCommitNumber = r4;
        this.mIntBuffer = r2;
        this.mObjBuffer = objArr;
        this.mIntBufferLen = r2 != null ? r2.length : 0;
        this.mObjBufferLen = objArr != null ? objArr.length : 0;
    }

    private void beginMarkers(String str) {
        Systrace.beginSection(0L, "FabricUIManager::" + str);
        if (this.mCommitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_START, null, this.mCommitNumber);
        }
    }

    private void endMarkers() {
        if (this.mCommitNumber > 0) {
            ReactMarker.logFabricMarker(ReactMarkerConstants.FABRIC_BATCH_EXECUTION_END, null, this.mCommitNumber);
        }
        Systrace.endSection(0L);
    }

    private static StateWrapper castToState(Object obj) {
        if (obj != null) {
            return (StateWrapper) obj;
        }
        return null;
    }

    private static EventEmitterWrapper castToEventEmitter(Object obj) {
        if (obj != null) {
            return (EventEmitterWrapper) obj;
        }
        return null;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(MountingManager mountingManager) {
        int r12;
        int r4;
        int r5;
        int r8;
        int r16;
        int r6;
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.m1326e(TAG, "Skipping batch of MountItems; no SurfaceMountingManager found for [%d].", Integer.valueOf(this.mSurfaceId));
        } else if (surfaceManager.isStopped()) {
            FLog.m1326e(TAG, "Skipping batch of MountItems; was stopped [%d].", Integer.valueOf(this.mSurfaceId));
        } else {
            if (FabricUIManager.ENABLE_FABRIC_LOGS) {
                FLog.m1339d(TAG, "Executing IntBufferBatchMountItem on surface [%d]", Integer.valueOf(this.mSurfaceId));
            }
            beginMarkers("mountViews");
            int r2 = 0;
            int r3 = 0;
            while (r2 < this.mIntBufferLen) {
                int[] r42 = this.mIntBuffer;
                int r52 = r2 + 1;
                int r22 = r42[r2];
                int r11 = r22 & (-2);
                if ((r22 & 1) != 0) {
                    int r43 = r42[r52];
                    r52++;
                    r12 = r43;
                } else {
                    r12 = 1;
                }
                int r13 = r3;
                r2 = r52;
                for (int r14 = 0; r14 < r12; r14++) {
                    if (r11 == 2) {
                        int r44 = r13 + 1;
                        String fabricComponentName = FabricComponents.getFabricComponentName((String) this.mObjBuffer[r13]);
                        int r62 = r2 + 1;
                        int r53 = this.mIntBuffer[r2];
                        Object[] objArr = this.mObjBuffer;
                        int r7 = r44 + 1;
                        Object obj = objArr[r44];
                        int r45 = r7 + 1;
                        r13 = r45 + 1;
                        r16 = r62 + 1;
                        surfaceManager.createView(fabricComponentName, r53, obj, castToState(objArr[r7]), castToEventEmitter(this.mObjBuffer[r45]), this.mIntBuffer[r62] == 1);
                    } else {
                        if (r11 == 4) {
                            surfaceManager.deleteView(this.mIntBuffer[r2]);
                            r2++;
                        } else {
                            if (r11 == 8) {
                                int[] r32 = this.mIntBuffer;
                                int r46 = r2 + 1;
                                int r54 = r46 + 1;
                                r6 = r54 + 1;
                                surfaceManager.addViewAt(r32[r46], r32[r2], r32[r54]);
                            } else if (r11 == 16) {
                                int[] r33 = this.mIntBuffer;
                                int r47 = r2 + 1;
                                int r55 = r47 + 1;
                                r6 = r55 + 1;
                                surfaceManager.removeViewAt(r33[r2], r33[r47], r33[r55]);
                            } else if (r11 == 2048) {
                                int[] r34 = this.mIntBuffer;
                                int r48 = r2 + 1;
                                int r56 = r48 + 1;
                                r6 = r56 + 1;
                                surfaceManager.removeDeleteTreeAt(r34[r2], r34[r48], r34[r56]);
                            } else {
                                if (r11 == 32) {
                                    r4 = r2 + 1;
                                    r5 = r13 + 1;
                                    surfaceManager.updateProps(this.mIntBuffer[r2], this.mObjBuffer[r13]);
                                } else if (r11 == 64) {
                                    r4 = r2 + 1;
                                    r5 = r13 + 1;
                                    surfaceManager.updateState(this.mIntBuffer[r2], castToState(this.mObjBuffer[r13]));
                                } else if (r11 == 128) {
                                    int[] r35 = this.mIntBuffer;
                                    int r49 = r2 + 1;
                                    int r57 = r35[r2];
                                    int r23 = r49 + 1;
                                    int r410 = r35[r49];
                                    int r63 = r23 + 1;
                                    int r72 = r35[r23];
                                    int r24 = r63 + 1;
                                    int r82 = r24 + 1;
                                    r16 = r82 + 1;
                                    surfaceManager.updateLayout(r57, r410, r72, r35[r63], r35[r24], r35[r82]);
                                } else {
                                    if (r11 == 512) {
                                        int[] r36 = this.mIntBuffer;
                                        int r411 = r2 + 1;
                                        int r58 = r36[r2];
                                        int r25 = r411 + 1;
                                        int r412 = r36[r411];
                                        int r64 = r25 + 1;
                                        int r73 = r36[r25];
                                        int r26 = r64 + 1;
                                        r8 = r26 + 1;
                                        surfaceManager.updatePadding(r58, r412, r73, r36[r64], r36[r26]);
                                    } else if (r11 == 1024) {
                                        int[] r37 = this.mIntBuffer;
                                        int r413 = r2 + 1;
                                        int r59 = r37[r2];
                                        int r27 = r413 + 1;
                                        int r414 = r37[r413];
                                        int r65 = r27 + 1;
                                        int r74 = r37[r27];
                                        int r28 = r65 + 1;
                                        r8 = r28 + 1;
                                        surfaceManager.updateOverflowInset(r59, r414, r74, r37[r65], r37[r28]);
                                    } else if (r11 == 256) {
                                        r4 = r2 + 1;
                                        r5 = r13 + 1;
                                        surfaceManager.updateEventEmitter(this.mIntBuffer[r2], castToEventEmitter(this.mObjBuffer[r13]));
                                    } else {
                                        throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + r11 + " at index: " + r2);
                                    }
                                    r2 = r8;
                                }
                                r2 = r4;
                                r13 = r5;
                            }
                            r2 = r6;
                        }
                    }
                    r2 = r16;
                }
                r3 = r13;
            }
            surfaceManager.didUpdateViews();
            endMarkers();
        }
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    public boolean shouldSchedule() {
        return this.mIntBufferLen != 0;
    }

    public String toString() {
        int r6;
        int r12;
        int r9;
        int r11;
        int r13;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("IntBufferBatchMountItem [surface:%d]:\n", Integer.valueOf(this.mSurfaceId)));
            int r3 = 0;
            int r5 = 0;
            while (r3 < this.mIntBufferLen) {
                int[] r62 = this.mIntBuffer;
                int r7 = r3 + 1;
                int r32 = r62[r3];
                int r8 = r32 & (-2);
                if ((r32 & 1) != 0) {
                    r6 = r62[r7];
                    r7++;
                } else {
                    r6 = 1;
                }
                r3 = r7;
                for (int r72 = 0; r72 < r6; r72++) {
                    if (r8 == 2) {
                        int r14 = r3 + 1;
                        r13 = r14 + 1;
                        sb.append(String.format("CREATE [%d] - layoutable:%d - %s\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r14]), FabricComponents.getFabricComponentName((String) this.mObjBuffer[r5])));
                        r5 = r5 + 1 + 3;
                    } else {
                        if (r8 == 4) {
                            r12 = r3 + 1;
                            sb.append(String.format("DELETE [%d]\n", Integer.valueOf(this.mIntBuffer[r3])));
                        } else if (r8 == 8) {
                            int r132 = r3 + 1;
                            int r122 = r132 + 1;
                            r13 = r122 + 1;
                            sb.append(String.format("INSERT [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r132]), Integer.valueOf(this.mIntBuffer[r122])));
                        } else if (r8 == 16) {
                            int r133 = r3 + 1;
                            int r123 = r133 + 1;
                            r13 = r123 + 1;
                            sb.append(String.format("REMOVE [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r133]), Integer.valueOf(this.mIntBuffer[r123])));
                        } else if (r8 == 2048) {
                            int r134 = r3 + 1;
                            int r124 = r134 + 1;
                            r13 = r124 + 1;
                            sb.append(String.format("REMOVE+DELETE TREE [%d]->[%d] @%d\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r134]), Integer.valueOf(this.mIntBuffer[r124])));
                        } else {
                            if (r8 == 32) {
                                r11 = r5 + 1;
                                Object obj = this.mObjBuffer[r5];
                                r12 = r3 + 1;
                                sb.append(String.format("UPDATE PROPS [%d]: %s\n", Integer.valueOf(this.mIntBuffer[r3]), "<hidden>"));
                            } else if (r8 == 64) {
                                r11 = r5 + 1;
                                castToState(this.mObjBuffer[r5]);
                                r12 = r3 + 1;
                                sb.append(String.format("UPDATE STATE [%d]: %s\n", Integer.valueOf(this.mIntBuffer[r3]), "<hidden>"));
                            } else {
                                if (r8 == 128) {
                                    int r16 = r3 + 1;
                                    int r15 = r16 + 1;
                                    int r162 = r15 + 1;
                                    int r10 = r162 + 1;
                                    int r92 = r10 + 1;
                                    sb.append(String.format("UPDATE LAYOUT [%d]: x:%d y:%d w:%d h:%d displayType:%d\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r16]), Integer.valueOf(this.mIntBuffer[r15]), Integer.valueOf(this.mIntBuffer[r162]), Integer.valueOf(this.mIntBuffer[r10]), Integer.valueOf(this.mIntBuffer[r92])));
                                    r3 = r92 + 1;
                                } else {
                                    if (r8 == 512) {
                                        int r152 = r3 + 1;
                                        int r142 = r152 + 1;
                                        int r153 = r142 + 1;
                                        int r102 = r153 + 1;
                                        r9 = r102 + 1;
                                        sb.append(String.format("UPDATE PADDING [%d]: top:%d right:%d bottom:%d left:%d\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r152]), Integer.valueOf(this.mIntBuffer[r142]), Integer.valueOf(this.mIntBuffer[r153]), Integer.valueOf(this.mIntBuffer[r102])));
                                    } else if (r8 == 1024) {
                                        int r154 = r3 + 1;
                                        int r143 = r154 + 1;
                                        int r155 = r143 + 1;
                                        int r103 = r155 + 1;
                                        r9 = r103 + 1;
                                        sb.append(String.format("UPDATE OVERFLOWINSET [%d]: left:%d top:%d right:%d bottom:%d\n", Integer.valueOf(this.mIntBuffer[r3]), Integer.valueOf(this.mIntBuffer[r154]), Integer.valueOf(this.mIntBuffer[r143]), Integer.valueOf(this.mIntBuffer[r155]), Integer.valueOf(this.mIntBuffer[r103])));
                                    } else if (r8 != 256) {
                                        String str = TAG;
                                        FLog.m1328e(str, "String so far: " + sb.toString());
                                        throw new IllegalArgumentException("Invalid type argument to IntBufferBatchMountItem: " + r8 + " at index: " + r3);
                                    } else {
                                        r5++;
                                        r12 = r3 + 1;
                                        sb.append(String.format("UPDATE EVENTEMITTER [%d]\n", Integer.valueOf(this.mIntBuffer[r3])));
                                    }
                                    r3 = r9;
                                }
                            }
                            r5 = r11;
                        }
                        r3 = r12;
                    }
                    r3 = r13;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            FLog.m1327e(TAG, "Caught exception trying to print", e);
            StringBuilder sb2 = new StringBuilder();
            for (int r33 = 0; r33 < this.mIntBufferLen; r33++) {
                sb2.append(this.mIntBuffer[r33]);
                sb2.append(", ");
            }
            FLog.m1328e(TAG, sb2.toString());
            for (int r2 = 0; r2 < this.mObjBufferLen; r2++) {
                String str2 = TAG;
                Object[] objArr = this.mObjBuffer;
                FLog.m1328e(str2, objArr[r2] != null ? objArr[r2].toString() : "null");
            }
            return "";
        }
    }
}
