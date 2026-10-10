// Smart Placement Portal - demo data migration
// Keeps existing browser LocalStorage data consistent after demo-name changes.
(function () {
    const COMPANY_MAP = {
        "TechNova Solutions": "TCS",
        "TechNova": "TCS",
        "DataMind Analytics": "Infosys",
        "DataMind": "Infosys",
        "InnovateX Technologies": "Accenture",
        "InnovateX": "Accenture",
        "CodeCraft Systems": "Capgemini",
        "CodeCraft": "Capgemini",
        "InfoWave": "Wipro",
        "CloudNex": "Wipro",
        "CodeSphere": "Capgemini",
        "CloudCore": "Wipro"
    };

    const replaceCompanies = (value) => {
        if (typeof value !== "string") return value;
        let result = value;
        Object.keys(COMPANY_MAP).forEach(oldName => {
            result = result.split(oldName).join(COMPANY_MAP[oldName]);
        });
        return result;
    };

    const replaceDeep = (value) => {
        if (typeof value === "string") return replaceCompanies(value);
        if (Array.isArray(value)) return value.map(replaceDeep);
        if (value && typeof value === "object") {
            Object.keys(value).forEach(key => {
                value[key] = replaceDeep(value[key]);
            });
        }
        return value;
    };

    try {
        for (let i = 0; i < localStorage.length; i++) {
            const key = localStorage.key(i);
            const raw = localStorage.getItem(key);
            if (!raw) continue;

            try {
                const parsed = JSON.parse(raw);
                localStorage.setItem(key, JSON.stringify(replaceDeep(parsed)));
            } catch (e) {
                localStorage.setItem(key, replaceCompanies(raw));
            }
        }

        // Keep the demo student's saved profile aligned with the new details.
        const profileRaw = localStorage.getItem("studentProfile");
        if (profileRaw) {
            const profile = JSON.parse(profileRaw);
            profile.name = "Om Yadav";
            profile.email = "omyadav@gmail.com";
            localStorage.setItem("studentProfile", JSON.stringify(profile));
        }
    } catch (e) {
        console.warn("Demo data migration skipped:", e);
    }
})();
