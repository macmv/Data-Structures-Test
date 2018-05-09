package net.cubiness.datastructurestest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;

public class ExperimentStructures {
    
    private final HashMap<ExperimentType, Collection<Account>> structures = new HashMap<>();
    public static final Logger log = Logger.getLogger(ExperimentStructures.class.getName());
    
    public ExperimentStructures() {
        log.info("Structure creation started");
        ArrayList<ExperimentType> types = new ArrayList<>();
        // "linkedList", "binaryTree", "hashSet"
        types.add(new ExperimentType("linkedList", 1000));
        types.add(new ExperimentType("linkedList", 500000));
        types.add(new ExperimentType("linkedList", 1000000));
        types.add(new ExperimentType("binaryTree", 1000));
        types.add(new ExperimentType("binaryTree", 500000));
        types.add(new ExperimentType("binaryTree", 1000000));
        types.add(new ExperimentType("hashSet"   , 1000));
        types.add(new ExperimentType("hashSet"   , 500000));
        types.add(new ExperimentType("hashSet"   , 1000000));
        for (ExperimentType type : types) {
            Collection<Account> struct = null;
            if (type.getType() == "linkedList") {
                struct = new LinkedList<>();
            } else if (type.getType() == "binaryTree") {
                struct = new TreeSet<>();
            } else if (type.getType() == "hashSet") {
                struct = new HashSet<>();
            }
            if (struct == null) {
                throw new RuntimeException("HOW DAMN STUPID ARE YOU??? (too the programmer, not the user, if you are seeing this)");
            }
            structures.put(type, struct);
        }
        log.info("Filling structures");
        fillLists();
        log.info("Structure creation complete");
    }

    private void fillLists() {
        structures.forEach((type, struct) -> {
            log.info("Filling structure " + type.getType() + " of length " + type.getLength());
            struct.add(new Account("0ec0d6cc-4ae7-4ab0-969a-42a33d74679b", "0942702c-2569-4faf-9a1e-58c84df9c11a"));
            struct.add(new Account("bfe85a65-ffdf-4640-b4f0-2f80afcb9f81", "2dd16346-f94a-4167-a269-de1885bdecb0"));
            struct.add(new Account("3eccd6ba-aa54-48ce-8622-210dace052d6", "155c36d6-40ed-462c-8cda-5547931cfd43"));
            for (int i = 0; i < type.getLength() - 3; i++) {
                struct.add(new Account(UUID.randomUUID().toString(), UUID.randomUUID().toString()));
            }
        });
    }

    public Collection<Account> getStructure(String type, int length) {
        return structures.get(new ExperimentType(type, length));
    }
    
    public class ExperimentType {
        private final String type;
        private final int length;
        public ExperimentType(String type, int length) {
            this.type = type;
            this.length = length;
        }
        public String getType() {
            return type;
        }
        public int getLength() {
            return length;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + length;
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }
        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ExperimentType other = (ExperimentType) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (length != other.length)
                return false;
            if (type == null) {
                if (other.type != null)
                    return false;
            } else if (!type.equals(other.type))
                return false;
            return true;
        }
        private ExperimentStructures getOuterType() {
            return ExperimentStructures.this;
        }
        
    }

}
